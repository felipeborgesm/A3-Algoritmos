package com.a3_backend.repository;

import com.a3_backend.dto.AllProductsResponse;
import com.a3_backend.dto.CreateProdutoRequest;
import com.a3_backend.dto.ProdutoResponse;
import com.a3_backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Produto findByCodigo (Integer codigo);
    List<Produto> getAllByEmpresaId (Long empresaId);
}
