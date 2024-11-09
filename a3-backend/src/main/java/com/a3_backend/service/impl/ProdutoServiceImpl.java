package com.a3_backend.service.impl;

import com.a3_backend.dto.*;
import com.a3_backend.model.Empresa;
import com.a3_backend.model.Pedido;
import com.a3_backend.model.Produto;
import com.a3_backend.repository.EmpresaRepository;
import com.a3_backend.repository.ProdutoRepository;
import com.a3_backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
public class ProdutoServiceImpl implements ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    EmpresaRepository empresaRepository;
    @Autowired
    PedidoServiceImpl pedidoServiceImpl;

    @Override
    public ProdutoResponse getByCodigo(Integer codigo) {
        Produto produto = produtoRepository.getByCodigo(codigo);
        return new ProdutoResponse(produto);
    }

    @Override
    public List<AllProductsResponse> getAllByEmpresaId(Long empresaId) {
        List<Produto> produtos = produtoRepository.getAllByEmpresaId(empresaId);

        List<AllProductsResponse> formattedProdutos = new ArrayList<>();

        produtos.forEach(item -> formattedProdutos.add(new AllProductsResponse(item)));

        return formattedProdutos;
    }

    @Override
    public void create(CreateProdutoRequest produtoRequest, Long empresaId) {
        Produto produto = new Produto(produtoRequest);

        var empresa = empresaRepository.findById(empresaId).orElseThrow();

        produto.setCodigo(gerarNumeroUnico());
        produto.setEmpresa(empresa);
        produto.setIsProductInEstoque(true);
        produtoRepository.save(produto);

        empresa.getProdutos().add(produto);
        empresaRepository.save(empresa);
    }

    @Override
    public void tradeProduto(TradeProdutoRequest tradeProdutoRequest, Long empresaId) {
        Produto produto = produtoRepository.getByCodigo(tradeProdutoRequest.getCodigo());
        if (produto == null) {
            throw new RuntimeException("Produto não encontrado");
        }
        CreatePedidoRequest createPedidoRequest = new CreatePedidoRequest();

        if (produto.getQuantidade() + tradeProdutoRequest.getQuantidade() <= 0) {
            int quantidadeReposicao = calcularQuantidadeReposicao(produto, tradeProdutoRequest.getQuantidade());

            createPedidoRequest.setProduto(produto);
            createPedidoRequest.setValorTotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(tradeProdutoRequest.getQuantidade())));
            createPedidoRequest.setQuantidade(quantidadeReposicao);
            createPedidoRequest.setIsPedidoFinalizado(false);

            Pedido pedido = pedidoServiceImpl.create(createPedidoRequest);
            produto.getPedidosToEstoque().add(pedido);
            produto.setIsProductInEstoque(false);

        } else {
            produto.setQuantidade(produto.getQuantidade() + tradeProdutoRequest.getQuantidade());

            createPedidoRequest.setProduto(produto);
            createPedidoRequest.setValorTotal(produto.getValorUnitario().multiply(BigDecimal.valueOf(tradeProdutoRequest.getQuantidade())));
            createPedidoRequest.setQuantidade(tradeProdutoRequest.getQuantidade());
            createPedidoRequest.setIsPedidoFinalizado(true);

            Pedido pedido = pedidoServiceImpl.create(createPedidoRequest);
            if (produto.getQuantidade() <= 0) {
                produto.setIsProductInEstoque(false);
            }
        }
    }

    private int calcularQuantidadeReposicao(Produto produto, int quantidadeVendida) {
        int quantidadeMinimaDesejada = 10;
        return quantidadeMinimaDesejada + quantidadeVendida + produto.getQuantidade();
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
