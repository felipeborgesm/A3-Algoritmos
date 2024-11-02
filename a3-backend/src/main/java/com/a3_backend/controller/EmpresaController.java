package com.a3_backend.controller;

import com.a3_backend.dto.*;
import com.a3_backend.service.EmpresaService;
import com.a3_backend.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    ProdutoService produtoService;

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

    @PutMapping("/funcionarios/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public EmpresaResponse addFuncionarios(@PathVariable Long id, @RequestBody AddFuncionariosRequest funcionariosId) { return empresaService.addFuncionarios(funcionariosId, id); }

    @PutMapping("/produto/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduto(@PathVariable Long id, @RequestBody CreateProdutoRequest produtoRequest) { produtoService.addProduto(produtoRequest, id); }



}
