package com.a3_backend.service.impl;

import com.a3_backend.TAD.TADFilaEncadeada;
import com.a3_backend.TAD.TADListaEncadeada;
import com.a3_backend.dto.*;
import com.a3_backend.model.Pedido;
import com.a3_backend.model.Produto;
import com.a3_backend.repository.EmpresaRepository;
import com.a3_backend.repository.PedidoRepository;
import com.a3_backend.repository.ProdutoRepository;
import com.a3_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    PedidoServiceImpl pedidoServiceImpl;
    @Autowired
    PedidoRepository pedidoRepository;

    final TADFilaEncadeada<Pedido> filaPedidos = new TADFilaEncadeada<>();

    @Override
    public TextoResponse tradeProduto(TradeProdutoRequest tradeProdutoRequest, Long empresaId) {
        Produto produto = produtoRepository.getByCodigo(tradeProdutoRequest.getCodigo());
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado");
        }
        CreatePedidoRequest createPedidoRequest = new CreatePedidoRequest();

        if (tradeProdutoRequest.getQuantidade() > 0) {
            produto.setQuantidade(produto.getQuantidade() + tradeProdutoRequest.getQuantidade());

            createPedidoRequest.setValorTotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(tradeProdutoRequest.getQuantidade())));
            createPedidoRequest.setQuantidade(tradeProdutoRequest.getQuantidade());
            createPedidoRequest.setIsPedidoFinalizado(true);

            Pedido pedido = pedidoServiceImpl.create(createPedidoRequest, produto);

            do {
                Pedido primeiroPedido = filaPedidos.primeiro();

                if (primeiroPedido != null && primeiroPedido.getQuantidade() <= produto.getQuantidade()) {
                    produto.setQuantidade(produto.getQuantidade() + primeiroPedido.getQuantidade());

                    CreatePedidoRequest createPedidoRequest2 = new CreatePedidoRequest();

                    createPedidoRequest2.setValorTotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(primeiroPedido.getQuantidade())));
                    createPedidoRequest2.setQuantidade(primeiroPedido.getQuantidade());
                    createPedidoRequest2.setIsPedidoFinalizado(true);

                    Pedido pedido2 = pedidoServiceImpl.create(createPedidoRequest2, produto);

                    filaPedidos.desenfileirar();
                } else { break; }
            } while (!filaPedidos.estaVazia());
            if (produto.getQuantidade() > 0) {
                produto.setIsProductInEstoque(true);
            }

            return new TextoResponse("Sucesso");
        }
        else if (produto.getQuantidade() + tradeProdutoRequest.getQuantidade() < 10) {
            createPedidoRequest.setValorTotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(tradeProdutoRequest.getQuantidade())));
            createPedidoRequest.setQuantidade(tradeProdutoRequest.getQuantidade());
            createPedidoRequest.setIsPedidoFinalizado(false);

            Pedido pedido = pedidoServiceImpl.create(createPedidoRequest, produto);
            filaPedidos.enfileirar(pedido);
            produto.setIsProductInEstoque(false);

            return new TextoResponse("Pedido fora de estoque");
        } else {
            produto.setQuantidade(produto.getQuantidade() + tradeProdutoRequest.getQuantidade());

            createPedidoRequest.setValorTotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(tradeProdutoRequest.getQuantidade())));
            createPedidoRequest.setQuantidade(tradeProdutoRequest.getQuantidade());
            createPedidoRequest.setIsPedidoFinalizado(true);

            Pedido pedido = pedidoServiceImpl.create(createPedidoRequest, produto);
            if (produto.getQuantidade() <= 0) {
                produto.setIsProductInEstoque(false);
            }
            return new TextoResponse("Sucesso");
        }
    }

    @Override
    public ProdutoResponse getByCodigo(Integer codigo) {
        Produto produto = produtoRepository.getByCodigo(codigo);
        return new ProdutoResponse(produto);
    }

    @Override
    public TADListaEncadeada<AllProductsResponse> getAllByEmpresaId(Long empresaId) {
        List<Produto> produtos = produtoRepository.getAllByEmpresaId(empresaId);

        TADListaEncadeada<AllProductsResponse> formattedProdutos = new TADListaEncadeada<>();
        produtos.forEach(item -> formattedProdutos.add(new AllProductsResponse(item)));

        return formattedProdutos;
    }

    @Override
    public CreateProdutoResponse create(CreateProdutoRequest produtoRequest, Long empresaId) {
        Produto produto = new Produto(produtoRequest);
        var empresa = empresaRepository.findById(empresaId).orElseThrow();

        produto.setCodigo(gerarNumeroUnico());
        produto.setEmpresa(empresa);
        produto.setIsProductInEstoque(true);
        produtoRepository.save(produto);

        empresa.getProdutos().add(produto);
        empresaRepository.save(empresa);

        return new CreateProdutoResponse(produto);
    }

    @Override
    public TADListaEncadeada<PedidoResponse> getAllEstoquesByEmpresaId(Long empresaId) {
        TADListaEncadeada<PedidoResponse> formattedProdutos = new TADListaEncadeada<>();
        for (Pedido pedido : filaPedidos) {
            if (Objects.equals(pedido.getProduto().getEmpresa().getId(), empresaId)) {
                formattedProdutos.add(new PedidoResponse(pedido));
            }
        }
        return formattedProdutos;
    }

    private Integer gerarNumeroUnico() {
        Random random = new Random();
        Integer numeroAleatorio;

        do {
            numeroAleatorio = random.nextInt(900000) + 100000;
        } while (!numeroEhUnico(numeroAleatorio));

        return numeroAleatorio;
    }

    private boolean numeroEhUnico(Integer codigo) {
        return produtoRepository.getByCodigo(codigo) == null;
    }
}
