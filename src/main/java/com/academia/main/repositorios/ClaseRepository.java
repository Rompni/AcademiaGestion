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

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

	Clase findClaseById(Integer Id);

	@Query("SELECT c FROM Clase c WHERE :alumno MEMBER OF c.alumnos")
	List<Clase> findClasesByAlumno(@Param("alumno") Alumno alumno);

	@Query("SELECT c FROM Clase c, Asignatura a WHERE a.curso.id = :cursox AND a.id = c.asignatura")
	List<Clase> findClasesByCurso(@Param("cursox") Long cursox);

	@Query("SELECT c FROM Clase c, Asignatura a WHERE a.id = :asignaturax AND :asignaturax = c.asignatura")
	List<Clase> findClasesByAsignatura(@Param("asignaturax") Long asignaturax);

	@Query("SELECT c FROM Clase c, Profesor p WHERE p.id = :profesorx AND :profesorx = c.profesor")
	List<Clase> findClasesByProfesor(@Param("profesorx") Long profesorx);

	@Query("SELECT c FROM Clase c, Asignatura a, Profesor p WHERE p.id = :profesorx AND a.id = :asignaturax AND a.curso.id = :cursox AND a.id = c.asignatura AND :asignaturax = c.asignatura AND :profesorx = c.profesor")
	List<Clase> findClasesByCursoAsignaturaProfesor(@Param("cursox") Long cursox,
			@Param("asignaturax") Long asignaturax, @Param("profesorx") Long profesorx);

	@Query("SELECT c FROM Clase c, Asignatura a, Profesor p WHERE a.curso.id = :cursox AND a.id = c.asignatura AND p.id = :profesorx AND :profesorx = c.profesor" )
	List<Clase> findClasesByCursoProfesor(@Param("cursox") Long cursox, @Param("profesorx") long profesorx);

	@Query("SELECT al FROM Alumno al WHERE al.cursoA.id = :cursox" )
	List<Alumno> findAlumnoByCursoClase(@Param("cursox") Long cursox);

}
