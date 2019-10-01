package com.academia.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Alumno;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

	Alumno findAlumnoById(Integer Id);
	
	@Query("SELECT a FROM Alumno a")
	List<Alumno> findAllAlumnos();
	
	@Query("SELECT a FROM Alumno a WHERE a.nombre = :nombre or a.id_curso = :Id")
	List<Alumno> findAlumnosByNameOrIdCurso(
		@Param("nombre") String nombre,
		@Param("Id") Integer Id);

	
	@Query("SELECT a FROM Alumno a WHERE a.fechabaja is not null")
	List<Alumno> findAlumnosbyFechabaja();
}
