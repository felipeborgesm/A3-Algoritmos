package com.a3_backend.service;

import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.CreateUsuarioRequest;
import com.a3_backend.dto.UsuarioResponse;
import com.a3_backend.model.Usuario;

public interface UsuarioService {
    UsuarioResponse getById(Long id);
    CreateEntityResponse create(CreateUsuarioRequest usuarioRequest);

}