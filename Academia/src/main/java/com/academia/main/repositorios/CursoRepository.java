package com.academia.main.repositorios;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Curso;
import com.academia.main.entidades.HoraSemanal;
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	@Value("${spring.queries.profesor-cursos}")
	List<HoraSemanal> findCursosByIdProfesor(
			@Param("Id") String Id);
}
