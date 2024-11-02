package com.a3_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddFuncionariosRequest {
    private List<Long> funcionariosId;
}
