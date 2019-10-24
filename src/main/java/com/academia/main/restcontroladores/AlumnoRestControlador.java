package com.academia.main.restcontroladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Alumno;
import com.academia.main.repositorios.AlumnoRepository;

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
public class AlumnoRestControlador {

	@Autowired
	private AlumnoRepository alumnorepositorio;

	@GetMapping("/Alumnos")
	public List<Alumno> getAlumnos(){
		return alumnorepositorio.findAll();

	}
	
	@PostMapping("/Alumnos")
	public Alumno crearAlumno(@RequestBody Alumno alumno) {
		return alumnorepositorio.save(alumno);
	}
	
	@GetMapping("/Alumnos/{id}")
	public Alumno getAlumnos(@PathVariable Long id) {
		Optional<Alumno> alumno = alumnorepositorio.findById(id);
		if(!alumno.isPresent()) {
			throw new EntityNotFoundException("No se encontro el Alumno con id "+id);
		}
				
		return alumno.get();
	}
	
	@PutMapping("/Alumnos")
	public Alumno updateAlumno(@RequestBody Alumno alumno) {
		return alumnorepositorio.save(alumno);
	}

	
	@DeleteMapping("/Alumnos/{id}")
	public void eliminar(@PathVariable Long id) {
		Alumno alumno = alumnorepositorio.getOne(id);
		alumnorepositorio.delete(alumno);

	}
	
}
