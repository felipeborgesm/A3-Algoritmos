package com.a3_backend.dto;

import com.a3_backend.TAD.ListaEncadeada;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateEmpresaRequest {
    private String nome;
    private String cnpj;
    private List<Long> funcionariosIds;
}
