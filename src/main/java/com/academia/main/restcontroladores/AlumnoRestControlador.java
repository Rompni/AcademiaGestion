package com.academia.main.restcontroladores;

import java.util.List;
import org.jboss.logging.Logger;

import com.academia.main.entidades.Alumno;
import com.academia.main.servicios.AlumnoServicio;


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

	@Autowired private AlumnoServicio alumnoservicio;

	@GetMapping("/Alumnos")
	public List<Alumno> getAlumnos(){
		return alumnoservicio.buscarAlumnos();
	}
	
	@PostMapping("/Alumnos")
	public Alumno crearAlumno( @RequestBody Alumno alumno) {
		return alumnoservicio.save(alumno);
	}
	
	@GetMapping("/Alumnos/{id}")
	public Alumno getAlumnos(@PathVariable Long id) {
		return alumnoservicio.BuscarAlumnoPorId(id);	
		
	}

	@GetMapping("/Alumnos/name/{nombre}")
	public List<Alumno> AlumnosbyName(@PathVariable String nombre) {
		return alumnoservicio.BuscarAlumnoPorNombre(nombre);	 
	}
	
	@PutMapping("/Alumnos")
	public Alumno updateAlumno(@RequestBody Alumno alumno) {
		return alumnoservicio.save(alumno);
	}

	
	@DeleteMapping("/Alumnos/{id}")
	public void eliminar(@PathVariable Long id) {
		Alumno alumno = alumnoservicio.BuscarAlumnoPorId(id);
		alumnoservicio.delete(alumno);
	}
	
}
