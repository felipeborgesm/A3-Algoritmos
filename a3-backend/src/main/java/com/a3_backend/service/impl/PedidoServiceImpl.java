package com.a3_backend.service.impl;

import com.a3_backend.TAD.TADListaEncadeada;
import com.a3_backend.dto.CreatePedidoRequest;
import com.a3_backend.dto.PedidoResponse;
import com.a3_backend.dto.ProdutoMaisVendidoResponse;
import com.a3_backend.model.Pedido;
import com.a3_backend.model.Produto;
import com.a3_backend.repository.PedidoRepository;
import com.a3_backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Pedido create(CreatePedidoRequest createPedidoRequest, Produto produto) {
        Pedido pedido = new Pedido(createPedidoRequest);
        pedido.setProduto(produto);

        pedidoRepository.save(pedido);
        return pedido;
    }

    @Override
    public List<PedidoResponse> getAllByProdutoCodigo(Integer codigo) {
        var listaPedidos = pedidoRepository.getAllByProdutoCodigo(codigo);

        List<PedidoResponse> pedidoResponse = new ArrayList<>();
        listaPedidos.forEach(item -> pedidoResponse.add(new PedidoResponse(item)));

        return pedidoResponse;
    }

    @Override
    public List<PedidoResponse> getAllByEmpresaId(Long empresaId) {
        var listaPedidos = pedidoRepository.getAllByEmpresaId(empresaId);

        List<PedidoResponse> pedidoResponse = new ArrayList<>();
        listaPedidos.forEach(item -> pedidoResponse.add(new PedidoResponse(item)));

        return pedidoResponse;
    }

    @Override
    public TADListaEncadeada<ProdutoMaisVendidoResponse> getProdutosMaisVendidos(Long empresaId) {
        List<Pedido> dadosPedidos = pedidoRepository.getAllByEmpresaId(empresaId);

        List<PedidoResponse> listaPedidos = new ArrayList<>();
        for (Pedido pedido : dadosPedidos) {
            if (pedido.getQuantidade() <= 0 && pedido.getIsPedidoFinalizado()) {
                listaPedidos.add(new PedidoResponse(pedido));
            }
        }

        class ProdutoResumo {
            Integer quantidade = 0;
            BigDecimal valorTotal = BigDecimal.ZERO;
        }
        Map<String, ProdutoResumo> mapaProdutos = new HashMap<>();

        for (PedidoResponse pedido : listaPedidos) {
            String produtoNome = pedido.getNome();
            ProdutoResumo resumo = mapaProdutos.computeIfAbsent(produtoNome, k -> new ProdutoResumo());

            resumo.quantidade += pedido.getQuantidade();
            resumo.valorTotal = resumo.valorTotal.add(pedido.getValorTotal());
        }

        TADListaEncadeada<ProdutoMaisVendidoResponse> resultado = new TADListaEncadeada<>();
        for (Map.Entry<String, ProdutoResumo> entry : mapaProdutos.entrySet()) {
            String produto = entry.getKey();
            ProdutoResumo resumo = entry.getValue();

            resultado.add(new ProdutoMaisVendidoResponse(produto, resumo.quantidade, resumo.valorTotal));
        }
        resultado.sort();

        return resultado;
    }
}
