package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProdutoRequest {
    private String nome;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private Boolean isPerecivel;
}
