package com.academia.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Curso;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

	Clase findClaseById(Integer Id);

	@Query("SELECT c FROM Clase c WHERE :alumno MEMBER OF c.alumnos")
	List<Clase> findClasesByAlumno(@Param("alumno") Alumno alumno);

	@Query("SELECT c FROM Clase c")
	List<Clase> findClasesByCurso(@Param("curso") Curso curso);

	// por asignatura

	// por profesor

}
