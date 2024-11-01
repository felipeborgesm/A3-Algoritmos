package com.a3_backend.service.impl;

import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.CreateUsuarioRequest;
import com.a3_backend.dto.UsuarioResponse;
import com.a3_backend.model.Usuario;
import com.a3_backend.repository.UsuarioRepository;
import com.a3_backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioSeviceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UsuarioResponse getById(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        return new UsuarioResponse(usuario);
    }

    @Override
    public CreateEntityResponse create(CreateUsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest);

        usuarioRepository.save(usuario);
        return new CreateEntityResponse(usuario);
    }
}
