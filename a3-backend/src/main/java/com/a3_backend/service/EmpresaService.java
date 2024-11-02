package com.a3_backend.service;

import com.a3_backend.dto.*;

import java.util.List;

public interface EmpresaService {
    EmpresaResponse getById(Long id);
    CreateEntityResponse create(CreateEmpresaRequest empresaRequest, Long id);
    EmpresaResponse addFuncionarios(AddFuncionariosRequest funcionariosId, Long empresaId);
}
