package com.academia.main.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.HoraSemanal;
import com.academia.main.entidades.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	
	Profesor findById(Integer id);
	
	@Value("${spring.queries.profesor-busqueda}")
	List<Profesor> findProfesorByNameOrNif(
			@Param("nombre") String nombre,
			@Param("nif") String nif);
	
	@Value("${spring.queries.profesor-busqueda2}")
	List<Profesor> findAllProfesors();
	
	
}
