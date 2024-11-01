package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUsuarioRequest {
    private String nome;
    private String login;
    private String senha;
}