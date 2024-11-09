package com.a3_backend.model;

import com.a3_backend.dto.CreateProdutoRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Table(name = "produto")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true)
    private Integer codigo;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "isProductInEstoque", nullable = false)
    private Boolean isProductInEstoque;

    @Column(name = "isPerecivel", nullable = false)
    private Boolean isPerecivel;

    @Column(name = "valorUnitario", nullable = false)
    private BigDecimal valorUnitario;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "dataAtualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    private Empresa empresa;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidosToEstoque = new ArrayList<>();

    public Produto (CreateProdutoRequest produtoRequest) {
        this.nome = produtoRequest.getNome();
        this.quantidade = produtoRequest.getQuantidade();
        this.valorUnitario = produtoRequest.getValorUnitario();
        this.isPerecivel = produtoRequest.getIsPerecivel();
        this.pedidosToEstoque = new ArrayList<>();
    }
}
