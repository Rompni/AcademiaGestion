package com.academia.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Curso;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

	Alumno findAlumnoById(Integer Id);
	
	@Query("SELECT a FROM Alumno a")
	List<Alumno> findAllAlumnos();
	
	@Query("SELECT a FROM Alumno a, Curso c WHERE a.nombre = :nombre or a.curso = :curso")
	List<Alumno> findAlumnosByNameOrCurso(
			@Param("curso") Curso curso,
			@Param("nombre") String nombre
			);

	@Query("SELECT a FROM Alumno a WHERE a.fechabaja IS NOT NULL")
	List<Alumno> findAlumnosByNotNullFechaBaja();
}
