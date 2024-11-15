package com.a3_backend.service;

import com.a3_backend.TAD.TADListaEncadeada;
import com.a3_backend.dto.*;

import java.util.List;

public interface ProdutoService {
    ProdutoResponse getByCodigo(Integer id);
    List<AllProductsResponse> getAllByEmpresaId(Long empresaId);
    void create(CreateProdutoRequest produtoRequest, Long empresaId);
    void tradeProduto(TradeProdutoRequest tradeProdutoRequest, Long empresaId);
    TADListaEncadeada<PedidoResponse> getAllEstoquesByEmpresaId(Long empresaId);
}
