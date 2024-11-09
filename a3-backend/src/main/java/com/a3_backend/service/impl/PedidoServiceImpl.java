package com.a3_backend.service.impl;

import com.a3_backend.dto.CreatePedidoRequest;
import com.a3_backend.dto.PedidoResponse;
import com.a3_backend.model.Pedido;
import com.a3_backend.repository.PedidoRepository;
import com.a3_backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Pedido create(CreatePedidoRequest createPedidoRequest) {
        Pedido pedido = new Pedido(createPedidoRequest);

        pedidoRepository.save(pedido);
        return pedido;
    }

    @Override
    public List<PedidoResponse> getAllByProdutoCodigo(Integer codigo) {
        var dados = pedidoRepository.getAllByProdutoCodigo(codigo);

        List<PedidoResponse> pedidoResponse = new ArrayList<>();
        dados.forEach(item -> pedidoResponse.add(new PedidoResponse(item)));

        return pedidoResponse;
    }

    @Override
    public List<PedidoResponse> getAllByEmpresaId(Long empresaId) {
        var dados = pedidoRepository.getAllByEmpresaId(empresaId);

        List<PedidoResponse> pedidoResponse = new ArrayList<>();
        dados.forEach(item -> pedidoResponse.add(new PedidoResponse(item)));

        return pedidoResponse;
    }
}
