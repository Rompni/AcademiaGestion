package com.academia.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Asignatura;
import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Curso;
import com.academia.main.entidades.Profesor;
@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

	Clase findClaseById(Integer Id);
	
	@Query("SELECT c FROM Clase c")
	List<Clase> findAllClases();
	
	@Query("SELECT c FROM Clase c WHERE :alumno MEMBER OF c.alumnos")
	List<Clase> findClasesByAlumno(
			@Param("alumno") Alumno alumno
			);

	//porcursp
	
	//por asignatura
	
	//por profesor
	
}
