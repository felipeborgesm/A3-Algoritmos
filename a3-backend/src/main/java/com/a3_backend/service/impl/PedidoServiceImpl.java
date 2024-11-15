package com.a3_backend.service.impl;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<ProdutoMaisVendidoResponse> getProdutosMaisVendidos(Long empresaId) {
        List<Pedido> dadosPedidos = pedidoRepository.getAllByEmpresaId(empresaId);

        List<PedidoResponse> listaPedidos = new ArrayList<>();
        dadosPedidos.forEach(item -> listaPedidos.add(new PedidoResponse(item)));

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

        List<ProdutoMaisVendidoResponse> resultado = new ArrayList<>();
        for (Map.Entry<String, ProdutoResumo> entry : mapaProdutos.entrySet()) {
            String produto = entry.getKey();
            ProdutoResumo resumo = entry.getValue();

            resultado.add(new ProdutoMaisVendidoResponse(produto, resumo.quantidade, resumo.valorTotal));
        }

        resultado.sort((a, b) -> {
            int compareQuantidade = a.getQuantidadeTotal().compareTo(b.getQuantidadeTotal());
            if (compareQuantidade != 0) {
                return compareQuantidade;
            } else {
                return a.getValorTotal().compareTo(b.getValorTotal());
            }
        });

        return resultado;
    }
}
