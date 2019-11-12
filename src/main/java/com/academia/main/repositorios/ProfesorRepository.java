package com.academia.main.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Profesor;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
	
	Profesor findById(Integer id);
	
	@Query("SELECT f FROM Profesor f")
	List<Profesor> findProfesores();
	
	@Query("SELECT f FROM Profesor f WHERE f.nombre = :nombre or f.nif = :nif")
	List<Profesor> findProfesorByNombreOrNif(
			@Param("nombre") String nombre,
			@Param("nif") String nif
			);
	
	Profesor findByNif (String nif);
	
	
}
