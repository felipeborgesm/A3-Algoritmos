package com.a3_backend.dto;

import com.a3_backend.TAD.ListaEncadeada;
import com.a3_backend.model.Empresa;
import com.a3_backend.model.Produto;
import com.a3_backend.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class EmpresaResponse {
    private String nome;
    private String cnpj;
    private String administrador;
    private List<String> funcionarios;
    private List<String> produtos;
    private String dataCriacao;
    private String dataAtualizacao;

    public EmpresaResponse(Empresa empresa) {
        this.nome = empresa.getNome();
        this.cnpj = empresa.getCnpj();
        this.funcionarios = empresa.getFuncionarios().stream()
                .map(Usuario::getNome)
                .collect(Collectors.toList());
        this.produtos = empresa.getProdutos().stream()
                .map(Produto::getNome)
                .collect(Collectors.toList());
        this.dataCriacao = formatarData(empresa.getDataCriacao());
        this.dataAtualizacao = formatarData(empresa.getDataAtualizacao());
        this.administrador = empresa.getAdministrador().getNome();
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
