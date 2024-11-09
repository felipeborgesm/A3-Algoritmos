package com.a3_backend.dto;

import com.a3_backend.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class AllProductsResponse {
    private Integer codigo;
    private String nome;
    private BigDecimal valorUnitario;
    private Integer quantidade;

    public AllProductsResponse(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.valorUnitario = produto.getValorUnitario();
        this.quantidade = produto.getQuantidade();
    }
}
