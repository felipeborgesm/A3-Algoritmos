package com.a3_backend.dto;

import com.a3_backend.model.Empresa;
import com.a3_backend.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioResponse {
    private String nome;
    private String login;
    private String dataCriacao;
    private String dataAtualizacao;
    private String empresa;

    public UsuarioResponse(Usuario usuario) {
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
        this.empresa = usuario.getEmpresa().getNome();
        this.dataCriacao = formatarData(usuario.getDataCriacao());
        this.dataAtualizacao = formatarData(usuario.getDataAtualizacao());
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
