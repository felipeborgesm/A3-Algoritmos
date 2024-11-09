package com.a3_backend.dto;

import com.a3_backend.model.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
public class PedidoResponse {
    private String nome;
    private Integer quantidade;
    private BigDecimal valorTotal;
    private Boolean isPedidoFinalizado;
    private String dataCriacao;

    public PedidoResponse(Pedido pedido) {
        this.nome = pedido.getProduto().getNome();
        this.quantidade = pedido.getQuantidade();
        this.valorTotal = pedido.getValorTotal();
        this.isPedidoFinalizado = pedido.getIsPedidoFinalizado();
        this.dataCriacao = formatarData(pedido.getDataCriacao());
    }

    private String formatarData(LocalDateTime data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return data.format(formatter);
    }
}
