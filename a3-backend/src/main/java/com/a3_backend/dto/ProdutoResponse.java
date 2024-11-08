package com.a3_backend.dto;

import com.a3_backend.model.Empresa;
import com.a3_backend.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class ProdutoResponse {
    private Integer codigo;
    private String nome;
    private BigDecimal valorUnitario;
    private Boolean isPerecivel;
    private Boolean isProductInEstoque;
    private String empresa;
    private String dataCriacao;
    private String dataAtualizacao;

    public ProdutoResponse(Produto produto) {
        this.codigo = produto.getCodigo();
        this.nome = produto.getNome();
        this.isPerecivel = produto.getIsPerecivel();
        this.isProductInEstoque = produto.getIsProductInEstoque();
        this.valorUnitario = produto.getValorUnitario();
        this.empresa = produto.getEmpresa().getNome();
        this.dataCriacao = formatarData(produto.getDataCriacao());
        this.dataAtualizacao = formatarData(produto.getDataAtualizacao());
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
