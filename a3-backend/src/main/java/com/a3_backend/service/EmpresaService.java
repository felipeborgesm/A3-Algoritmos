package com.a3_backend.service;

import com.a3_backend.dto.CreateEmpresaRequest;
import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.EmpresaResponse;

public interface EmpresaService {
    EmpresaResponse getById(Long id);
    CreateEntityResponse create(CreateEmpresaRequest empresaRequest, Long id);
}
