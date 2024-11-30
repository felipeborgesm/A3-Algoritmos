package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuarioResponse {
    private String nome;
    private String token;
    private Long id;

    public LoginUsuarioResponse(String nome, String token, Long id) {
        this.nome = nome;
        this.token = token;
        this.id = id;
    };
}
