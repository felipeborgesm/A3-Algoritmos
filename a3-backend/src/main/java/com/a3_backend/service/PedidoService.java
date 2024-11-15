package com.a3_backend.service;

import com.a3_backend.TAD.TADListaEncadeada;
import com.a3_backend.dto.CreatePedidoRequest;
import com.a3_backend.dto.PedidoResponse;
import com.a3_backend.dto.ProdutoMaisVendidoResponse;
import com.a3_backend.model.*;
import java.util.List;

public interface PedidoService {
    Pedido create(CreatePedidoRequest createPedidoRequest, Produto produto);
    List<PedidoResponse> getAllByProdutoCodigo(Integer codigo);
    List<PedidoResponse> getAllByEmpresaId(Long empresaId);
    TADListaEncadeada<ProdutoMaisVendidoResponse> getProdutosMaisVendidos(Long empresaId);
}
