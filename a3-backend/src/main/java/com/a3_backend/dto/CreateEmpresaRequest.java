package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmpresaRequest {
    private String nome;
    private String cnpj;
}
