package com.a3_backend.model;

import com.a3_backend.dto.CreatePedidoRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "pedido")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valorTotal")
    private BigDecimal valorTotal;

    @Column(name = "isPedidoFinalizado")
    private Boolean isPedidoFinalizado;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "dataAtualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Produto produto;

    public Pedido (CreatePedidoRequest pedidoRequest) {
        this.valorTotal = pedidoRequest.getValorTotal();
        this.quantidade = pedidoRequest.getQuantidade();
        this.isPedidoFinalizado = pedidoRequest.getIsPedidoFinalizado();
    }
}
