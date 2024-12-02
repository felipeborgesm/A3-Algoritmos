package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TextoResponse {
    private String texto;

    public TextoResponse(String value) {
        this.texto = value;
    }
}