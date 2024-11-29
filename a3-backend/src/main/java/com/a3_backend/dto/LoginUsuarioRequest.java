package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuarioRequest {
    private String login;
    private String senha;
}
