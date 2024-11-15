package com.a3_backend.dto;

import com.a3_backend.model.Produto;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreatePedidoRequest {
    private BigDecimal valorTotal;
    private Integer quantidade;
    private Boolean isPedidoFinalizado;
}
