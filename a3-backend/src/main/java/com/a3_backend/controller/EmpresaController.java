package com.a3_backend.controller;

import com.a3_backend.TAD.TADListaEncadeada;
import com.a3_backend.dto.*;
import com.a3_backend.service.EmpresaService;
import com.a3_backend.service.PedidoService;
import com.a3_backend.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    ProdutoService produtoService;

    @Autowired
    PedidoService pedidoService;

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
    public void addFuncionarios(@PathVariable Long id, @RequestBody AddFuncionariosRequest funcionariosId) { empresaService.addFuncionarios(funcionariosId, id); }

    @PutMapping("/produto/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduto(@PathVariable Long id, @RequestBody CreateProdutoRequest produtoRequest) { produtoService.create(produtoRequest, id); }

    @GetMapping("/produtos/{id}")
    @Operation(summary = "Retorna os dados do usuário de acordo o Id", description = "Retorna os dados do usuário")
    public List<AllProductsResponse> getAllProdutos(@PathVariable Long id) {
        return produtoService.getAllByEmpresaId(id);
    }

    @PutMapping("/produto/pedido/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void compraVendaProduto(@PathVariable Long id, @RequestBody TradeProdutoRequest tradeRequest) { produtoService.tradeProduto(tradeRequest, id); }

    @GetMapping("/produto/pedidos/{codigo}")
    @Operation(summary = "Retorna todos os pedidos pelo Codigo do produto", description = "Retorna todos os pedidos")
    public List<PedidoResponse> getAllProdutoPedidos(@PathVariable Integer codigo) {
        return pedidoService.getAllByProdutoCodigo(codigo);
    }

    @GetMapping("/pedidos/{id}")
    @Operation(summary = "Retorna todos os pedidos da empresa pelo Id da empresa", description = "Retorna todos os pedidos")
    public List<PedidoResponse> getAllPedidos(@PathVariable Long id) {
        return pedidoService.getAllByEmpresaId(id);
    }

    @GetMapping("/produto/mais-vendidos/{id}")
    @Operation(summary = "Retorna os produtos mais vendidos pela Id da empresa", description = "Retorna todos os pedidos")
    public TADListaEncadeada<ProdutoMaisVendidoResponse> getProdutoMaisVendido(@PathVariable Long id) {
        return pedidoService.getProdutosMaisVendidos(id);
    }

    @GetMapping("/produto/pedidos-estoque/{id}")
    @Operation(summary = "Retorna os produtos mais vendidos pela Id da empresa", description = "Retorna todos os pedidos")
    public TADListaEncadeada<PedidoResponse> getAllEstoquesByEmpresaId(@PathVariable Long id) {
        return produtoService.getAllEstoquesByEmpresaId(id);
    }
}
