package com.a3_backend.controller;

import com.a3_backend.dto.CreateEntityResponse;
import com.a3_backend.dto.CreateUsuarioRequest;
import com.a3_backend.dto.UsuarioResponse;
import com.a3_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria um usuário", description = "Retorna o Id do usuário criado")
    public CreateEntityResponse create(@RequestBody CreateUsuarioRequest usuarioRequest) {
        return usuarioService.create(usuarioRequest);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados do usuário de acordo o Id", description = "Retorna os dados do usuário")
    public UsuarioResponse getById(@PathVariable Long id) {
        return usuarioService.getById(id);
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World!";
    }
}
