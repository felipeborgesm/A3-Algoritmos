package com.a3_backend.service;

import com.a3_backend.dto.AllProductsResponse;
import com.a3_backend.dto.CreateProdutoRequest;
import com.a3_backend.dto.ProdutoResponse;
import com.a3_backend.dto.TradeProdutoRequest;
import java.util.List;

public interface ProdutoService {
    ProdutoResponse getByCodigo(Integer id);
    List<AllProductsResponse> getAllByEmpresaId(Long empresaId);
    void create(CreateProdutoRequest produtoRequest, Long empresaId);
    void tradeProduto(TradeProdutoRequest tradeProdutoRequest, Long empresaId);
}
