package com.academia.main.repositorios;

import java.util.List;

import com.academia.main.entidades.Alumno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre" )
    List<Alumno> BuscarAlumnoPorNombreCurso(
        @Param("nombre") String nombre);
    
}
