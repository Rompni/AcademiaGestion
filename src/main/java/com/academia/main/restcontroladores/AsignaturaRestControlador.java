package com.academia.main.restcontroladores;

import java.util.List;

import com.academia.main.entidades.Asignatura;
import com.academia.main.servicios.AsignaturaServicio;

import org.jboss.logging.Logger;
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
public class AsignaturaRestControlador {

	@Autowired
	private AsignaturaServicio AsignaturaServicio;

	@GetMapping("/Asignaturas")
	public List<Asignatura> getAsignaturas(){
		return AsignaturaServicio.buscarAsignaturas();
	}
	
	@PostMapping("/Asignaturas")
	public Asignatura crearAsignatura(@RequestBody Asignatura Asignatura) {
		return AsignaturaServicio.save(Asignatura);
	}
	
	@GetMapping("/Asignaturas/{id}")
	public Asignatura getAsignaturas(@PathVariable Long id) {
		return AsignaturaServicio.BuscarAsignaturaPorId(id);	
		
	}

	@GetMapping("/Asignaturas/name/{nombre}")
	public List<Asignatura> AsignaturasbyName(@PathVariable String nombre) {
		return AsignaturaServicio.BuscarAsignaturaPorNombre(nombre);	 
	}

	@GetMapping("/Asignaturas/curso/{id}")
	public List<Asignatura> AsignaturasbyCurso(@PathVariable String id) {
		return AsignaturaServicio.BuscarAsignaturaPorCurso(id);	 
	}
	
	

	@PutMapping("/Asignaturas")
	public Asignatura updateAsignatura(@RequestBody Asignatura Asignatura) {
		Asignatura asignatura = AsignaturaServicio.BuscarAsignaturaPorId(Asignatura.getId());
		asignatura.setNombre(Asignatura.getNombre());
		asignatura.setCurso(Asignatura.getCurso());
		asignatura.setClases(Asignatura.getClases());
		return AsignaturaServicio.save(asignatura);
	}

	
	@DeleteMapping("/Asignaturas/{id}")
	public void eliminar(@PathVariable Long id) {
		Asignatura Asignatura = AsignaturaServicio.BuscarAsignaturaPorId(id);
		AsignaturaServicio.delete(Asignatura);
	}
	
}