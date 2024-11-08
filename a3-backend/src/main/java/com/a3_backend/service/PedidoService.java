package com.a3_backend.service;

import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.CreatePedidoRequest;
import com.a3_backend.model.Pedido;

public interface PedidoService {
    Pedido create(CreatePedidoRequest createPedidoRequest);
}
