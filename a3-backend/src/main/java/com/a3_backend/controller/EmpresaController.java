package com.a3_backend.controller;

import com.a3_backend.dto.*;
import com.a3_backend.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Cria uma empresa", description = "Retorna o Id da empresa criada")
    public CreateEntityResponse create(@RequestBody CreateEmpresaRequest empresaRequest, @PathVariable Long id) {
        return empresaService.create(empresaRequest, id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna os dados do usuário de acordo o Id", description = "Retorna os dados do usuário")
    public EmpresaResponse getById(@PathVariable Long id) {
        return empresaService.getById(id);
    }

}
