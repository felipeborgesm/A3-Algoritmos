package com.a3_backend.service;

import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.CreateProdutoRequest;
import com.a3_backend.dto.ProdutoResponse;

import java.util.List;

public interface ProdutoService {
    ProdutoResponse getByCodigo(Integer id);
    List<ProdutoResponse> getAllByEmpresaId(Long empresaId);
    void addProduto(CreateProdutoRequest produtoRequest, Long empresaId);
}
