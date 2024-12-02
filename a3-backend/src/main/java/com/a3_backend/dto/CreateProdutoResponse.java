package com.a3_backend.dto;

import com.a3_backend.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProdutoResponse {
    private Integer codigo;

    public CreateProdutoResponse(Produto produto) {
        this.codigo = produto.getCodigo();
    }
}
