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

import com.academia.main.entidades.Responsable;
import com.academia.main.repositorios.ResponsableRepository;

@RestController
@RequestMapping("/api/v1")
public class ResponsableRestControlador {

	@Autowired
	private ResponsableRepository Responsablerepositorio;

	@GetMapping("/Responsables")
	public List<Responsable> getResponsables(){
		return Responsablerepositorio.findAll();
	}
	
	@PostMapping("/Responsables")
	public Responsable crearResponsable(@RequestBody Responsable Responsable) {
		return Responsablerepositorio.save(Responsable);
	}
	
	@GetMapping("/Responsables/{id}")
	public Responsable getResponsables(@PathVariable Long id) {
		Optional<Responsable> Responsable = Responsablerepositorio.findById(id);
		if(!Responsable.isPresent()) {
			throw new EntityNotFoundException("No se encontro el Responsable con id "+id);
		}
				
		return Responsable.get();
	}
	
	@PutMapping("/Responsables")
	public Responsable updateResponsable(@RequestBody Responsable Responsable) {
		return Responsablerepositorio.save(Responsable);
	}
	
	@DeleteMapping("/Responsables/{id}")
	public void eliminar(@PathVariable Long id) {
		Responsable Responsable = Responsablerepositorio.getOne(id);
		Responsablerepositorio.delete(Responsable);

	}
	
}
