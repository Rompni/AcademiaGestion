package com.academia.main.repositorios;

import java.util.List;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Clase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    @Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre")
    List<Alumno> findAlumnoByNombre(@Param("nombre") String nombre);

    @Query("SELECT a FROM Alumno a WHERE a.cursoA.id = :id")
    List<Alumno> findAlumnoByCurso(@Param("id") Long id);

    @Query("SELECT a FROM Alumno a WHERE a.cursoA.id = :id and a.nombre = :nombre")
    List<Alumno> findAlumnoByCursoAndNombre(@Param("id") Long id, @Param("nombre") String nombre);

    @Query("SELECT a FROM Alumno a WHERE a.nif = :nif")
    Alumno findAlumnoByNif(@Param("nif") String nif);

    @Query("SELECT c FROM Clase c, Asignatura a, Alumno al WHERE a.curso.id = :cursox AND a.id = c.asignatura AND al.id = :alumnox" )
    List<Clase> findClaseByCurso(@Param("cursox") Long cursox, @Param("alumnox") long alumnox);

}
