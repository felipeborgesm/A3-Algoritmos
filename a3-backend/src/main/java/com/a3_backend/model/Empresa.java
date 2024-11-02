package com.a3_backend.model;

//import com.a3_backend.TAD.ListaEncadeada;
//import com.a3_backend.TAD.ListaEncadeadaType;
//import org.hibernate.annotations.CollectionType;
import com.a3_backend.dto.CreateEmpresaRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "empresa")
@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private String cnpj;

    @Column(name = "dataCriacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "dataAtualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @ManyToOne
    @JoinColumn(name = "administrador_id", referencedColumnName = "id", unique = true)
    private Usuario administrador;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Usuario> funcionarios = new ArrayList<>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Produto> produtos = new ArrayList<>();

    public Empresa(CreateEmpresaRequest empresaRequest) {
        this.nome = empresaRequest.getNome();
        this.cnpj = empresaRequest.getCnpj();
    };
}


//    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL)
//    @CollectionType(type = ListaEncadeadaType.class)
//    private ListaEncadeada<Usuario> funcionarios;