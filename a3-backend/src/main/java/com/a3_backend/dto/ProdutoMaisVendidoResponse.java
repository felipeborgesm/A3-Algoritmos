package com.a3_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoMaisVendidoResponse {
    private String nome;
    private Integer quantidadeTotal;
    private BigDecimal valorTotal;
}
