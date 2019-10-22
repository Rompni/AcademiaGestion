package com.academia.main.restcontroladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Profesor;
import com.academia.main.repositorios.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProfesorRestControlador {

	@Autowired
	private ProfesorRepository Profesorrepositorio;

	@GetMapping("/Profesores")
	public List<Profesor> getProfesores(){
		return Profesorrepositorio.findAll();
	}
	
	@PostMapping("/Profesores")
	public Profesor crearProfesor(@RequestBody Profesor Profesor) {
		return Profesorrepositorio.save(Profesor);
	}
	
	@GetMapping("/Profesores/{id}")
	public Profesor getProfesor(@PathVariable Long id) {
		Optional<Profesor> Profesor = Profesorrepositorio.findById(id);
		if(!Profesor.isPresent()) {
			throw new EntityNotFoundException("No se encontro el Profesor con id "+id);
		}
				
		return Profesor.get();
	}
	
	@PutMapping("/Profesores")
	public Profesor updateProfesor(@RequestBody Profesor Profesor) {
		return Profesorrepositorio.save(Profesor);
	}
	
	@DeleteMapping("/Profesores/{id}")
	public void eliminar(@PathVariable Long id) {
		Profesor Profesor = Profesorrepositorio.getOne(id);
		Profesorrepositorio.delete(Profesor);

	}
	
}