package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.academia.main.entidades.Asignatura;
@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

	@Query("SELECT a FROM Asignatura a WHERE a.nombre = :nombre" )
    List<Asignatura> findAsignaturaByNombre(
        @Param("nombre") String nombre);

    @Query("SELECT a FROM Asignatura a WHERE a.curso.nivel = :nivel and a.curso.etapa = :etapa" )
    List<Asignatura> findAsignaturaByCurso(
        @Param("nivel") String nivel,
        @Param("etapa") String etapa);

}
