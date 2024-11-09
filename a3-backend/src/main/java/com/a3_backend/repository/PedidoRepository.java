package com.a3_backend.repository;

import com.a3_backend.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query("SELECT DISTINCT p FROM Pedido p " +
            "LEFT JOIN FETCH p.produto " +
            "WHERE p.produto.codigo = :codigo")
    List<Pedido> getAllByProdutoCodigo(@Param("codigo") Integer codigo);

    @Query("SELECT DISTINCT p FROM Pedido p " +
            "LEFT JOIN FETCH p.produto prod " +
            "LEFT JOIN FETCH prod.empresa " +
            "WHERE prod.empresa.id = :empresaId")
    List<Pedido> getAllByEmpresaId(@Param("empresaId") Long empresaId);
}
