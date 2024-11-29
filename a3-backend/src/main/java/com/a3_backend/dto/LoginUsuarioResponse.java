package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuarioResponse {
    private String nome;
    private String token;

    public LoginUsuarioResponse(String nome, String token) {
        this.nome = nome;
        this.token = token;
    };
}
