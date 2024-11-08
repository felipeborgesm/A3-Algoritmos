package com.a3_backend.model;

import com.a3_backend.dto.CreatePedidoRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.math.BigDecimal;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "valorTotal")
    private BigDecimal valorTotal;

    @Column(name = "isPedidoFinalizado")
    private Boolean isPedidoFinalizado;

    public Pedido (CreatePedidoRequest pedidoRequest) {
        this.produto = pedidoRequest.getProduto();
        this.valorTotal = pedidoRequest.getValorTotal();
        this.quantidade = pedidoRequest.getQuantidade();
    }
}
