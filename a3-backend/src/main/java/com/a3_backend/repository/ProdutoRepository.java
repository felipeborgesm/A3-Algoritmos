package com.a3_backend.repository;

import com.a3_backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto getByCodigo (Integer codigo);
    List<Produto> getAllByEmpresaId (Long empresaId);
}
