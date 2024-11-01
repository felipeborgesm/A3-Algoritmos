package com.a3_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateEntityResponse<T> { // Classe genérica
    private Long id;

    public CreateEntityResponse(T entity) { // Construtor genérico
        try {
            this.id = (Long) entity.getClass().getMethod("getId").invoke(entity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao obter o ID da entidade", e);
        }
    }
}