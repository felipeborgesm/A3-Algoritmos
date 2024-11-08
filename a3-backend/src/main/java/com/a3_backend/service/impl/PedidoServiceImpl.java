package com.a3_backend.service.impl;

import com.a3_backend.dto.CreatePedidoRequest;
import com.a3_backend.model.Pedido;
import com.a3_backend.repository.PedidoRepository;
import com.a3_backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
