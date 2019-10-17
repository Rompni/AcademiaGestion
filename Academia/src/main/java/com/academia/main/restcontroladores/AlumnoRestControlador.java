package com.academia.main.restcontroladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academia.main.entidades.Alumno;
import com.academia.main.repositorios.AlumnoRepository;


@RestController
@RequestMapping("/api/v1")
public class AlumnoRestControlador {

	@Autowired
	private AlumnoRepository alumnorepositorio;

	@GetMapping("/alumnos")
	public List<Alumno> getEstudiantes(){
		return alumnorepositorio.findAll();
	}
	
	@PostMapping("/alumnos")
	public Alumno crearEstudiante(@RequestBody Alumno alumno) {
		return alumnorepositorio.save(alumno);
	}
	
	@GetMapping("/alumnos/{id}")
	public Alumno getEstudiante(@PathVariable Long id) {
		Optional<Alumno> alumno = alumnorepositorio.findById(id);
		if(!alumno.isPresent()) {
			throw new EntityNotFoundException("No se encontro el estudiante con id "+id);
		}
				
		return alumno.get();
	}
	
	@PutMapping("/alumnos")
	public Alumno updateEstudiante(@RequestBody Alumno alumno) {
		return alumnorepositorio.save(alumno);
	}
	
	@DeleteMapping("/alumnos/{id}")
	public void eliminar(@PathVariable Long id) {
		Alumno alumno = alumnorepositorio.getOne(id);
		alumnorepositorio.delete(alumno);

	}
	
}
