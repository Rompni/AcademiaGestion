package com.academia.main.restcontroladores;

import java.util.List;

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
import com.academia.main.entidades.Responsable;
import com.academia.main.servicios.ResponsableServicio;

@RestController
@RequestMapping("/api/v1")
public class ResponsableRestControlador {

	@Autowired
	private ResponsableServicio Responsableservicio;

	@GetMapping("/Responsables")
	public List<Responsable> getResponsables(){
		return Responsableservicio.buscarResponsables();
	}
	
	@PostMapping("/Responsables")
	public Responsable crearResponsable(@RequestBody Responsable Responsable) {
		return Responsableservicio.save(Responsable);
	}
	
	@GetMapping("/Responsables/{id}")
	public Responsable getResponsables(@PathVariable Long id) {
		return Responsableservicio.BuscarResponsablePorId(id);		
	}

	@GetMapping("/Responsables/name/{nombre}")
	public List<Responsable> ResponsablesbyName(@PathVariable String nombre) {
		return Responsableservicio.BuscarResponsablePorNombre(nombre);	 
	}

	@GetMapping("/Responsables/alumnos/{id}")
	public List<Alumno> ResponsablesbyName(@PathVariable Long id) {
		return Responsableservicio.getAlumnosResponsable(id);	 
	}
	
	@PutMapping("/Responsables")
	public Responsable updateResponsable(@RequestBody Responsable Responsable) {
		return Responsableservicio.save(Responsable);
	}
	
	@DeleteMapping("/Responsables/{id}")
	public void eliminar(@PathVariable Long id) {
		Responsable Responsable = Responsableservicio.BuscarResponsablePorId(id);
		Responsableservicio.delete(Responsable);
	}
	
}
