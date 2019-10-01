package com.academia.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Asignatura;
import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Curso;
import com.academia.main.entidades.Profesor;
@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

	Clase findClaseById(Integer Id);
	
	@Query("SELECT c FROM Clase c")
	List<Clase> findAllClases();
	
	List<Clase> findClasesByCurso(Curso curso);
	
	List<Clase> findClasesByAsignatura(Asignatura asignatura);
	
	List<Clase> findClasesByProfesor(Profesor profesor);
	
	@Query("SELECT c, a FROM Clase c, Alumno a WHERE c.id_alumno = :Id AND a.fechabaja is null")
	List<Clase> findClasesByIdAlumno(
			@Param("Id") Integer Id);
}
