package com.a3_backend.repository;

import com.a3_backend.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    @Query("SELECT DISTINCT e FROM Empresa e " +
            "LEFT JOIN FETCH e.funcionarios f " +
            "WHERE e.id = :id")
    Optional<Empresa> findByIdWithFuncionarios(@Param("id") Long id);

}
