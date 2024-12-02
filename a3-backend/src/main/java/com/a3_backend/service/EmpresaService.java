package com.a3_backend.service;

import com.a3_backend.dto.*;

public interface EmpresaService {
    EmpresaResponse getById(Long id);
    CreateEntityResponse create(CreateEmpresaRequest empresaRequest, Long id);
    TextoResponse addFuncionarios(AddFuncionariosRequest funcionariosId, Long empresaId);
}
