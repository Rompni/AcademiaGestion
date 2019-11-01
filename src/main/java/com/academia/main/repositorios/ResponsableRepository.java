package com.academia.main.repositorios;

import java.util.List;

import com.academia.main.entidades.Responsable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {

    @Query("SELECT a FROM Responsable a WHERE a.nombre = :nombre" )
    List<Responsable> findResponsablebyNombreCurso(
        @Param("nombre") String nombre);
}
