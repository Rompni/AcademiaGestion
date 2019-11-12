package com.academia.main.restcontroladores;

import java.util.List;

import javax.validation.Valid;

import com.academia.main.entidades.Profesor;
import com.academia.main.servicios.ProfesorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ProfesorRestControlador {

    @Autowired
	private ProfesorServicio profesorservicio;

	@GetMapping("/Profesores")
	public List <Profesor> getProfesores(){
		return profesorservicio.buscarProfesores();
	}
	
	@PostMapping("/Profesores")
	@ResponseBody
	public Profesor crearProfesor(@Valid @RequestBody Profesor profesor) throws Exception{
		Profesor p = profesorservicio.BuscarProfesorPorNif(profesor.getNif());
		if(p != null) throw new Exception("Ya hay almacenado un profesor con ese NIF.");

		return profesorservicio.save(profesor);
	}
	
	@GetMapping("/Profesores/{id}")
	public Profesor getProfesores(@PathVariable Long id) {
		return profesorservicio.BuscarProfesorPorId(id);	
		
	}

	@GetMapping("/Profesores/namenif/{nombre}-{nif}")
	public List<Profesor> ProfesoresbyName(@PathVariable String nombre, @PathVariable String nif) {
		return profesorservicio.BuscarProfesorPorNombre(nombre, nif); 
	}
	
	@PutMapping("/Profesores")
	public Profesor updateProfesor(@RequestBody Profesor profesor) {
		return profesorservicio.save(profesor);
	}

	
	@DeleteMapping("/Profesores/{id}")
	public void eliminar(@PathVariable Long id) {
		Profesor profesor = profesorservicio.BuscarProfesorPorId(id);
		profesorservicio.delete(profesor);
	}
}