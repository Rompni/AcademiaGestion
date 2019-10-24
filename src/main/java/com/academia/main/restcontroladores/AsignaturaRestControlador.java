package com.academia.main.restcontroladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Asignatura;
import com.academia.main.repositorios.AsignaturaRepository;

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
	private AsignaturaRepository Asignaturarepositorio;

	@GetMapping("/Asignaturas")
	public List<Asignatura> getAsignaturas(){
		return Asignaturarepositorio.findAll();
	}
	
	@PostMapping("/Asignaturas")
	public Asignatura crearAsignatura(@RequestBody Asignatura Asignatura) {
		return Asignaturarepositorio.save(Asignatura);
	}
	
	@GetMapping("/Asignaturas/{id}")
	public Asignatura getAsignatura(@PathVariable Long id) {
		Optional<Asignatura> Asignatura = Asignaturarepositorio.findById(id);
		if(!Asignatura.isPresent()) {
			throw new EntityNotFoundException("No se encontro la Asignatura con id "+id);
		}
				
		return Asignatura.get();
	}
	
	@PutMapping("/Asignaturas")
	public Asignatura updateAsignatura(@RequestBody Asignatura Asignatura) {
		return Asignaturarepositorio.save(Asignatura);
	}
	
	@DeleteMapping("/Asignaturas/{id}")
	public void eliminar(@PathVariable Long id) {
		Asignatura Asignatura = Asignaturarepositorio.getOne(id);
		Asignaturarepositorio.delete(Asignatura);

	}
	
}