package com.academia.main.restcontroladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Clase;
import com.academia.main.repositorios.ClaseRepository;

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
public class ClaseRestControlador {

	@Autowired
	private ClaseRepository Claserepositorio;

	@GetMapping("/Clases")
	public List<Clase> getClases(){
		return Claserepositorio.findAll();
	}
	
	@PostMapping("/Clases")
	public Clase crearClase(@RequestBody Clase Clase) {
		return Claserepositorio.save(Clase);
	}
	
	@GetMapping("/Clases/{id}")
	public Clase getClase(@PathVariable Long id) {
		Optional<Clase> Clase = Claserepositorio.findById(id);
		if(!Clase.isPresent()) {
			throw new EntityNotFoundException("No se encontro la Clase con id "+id);
		}
				
		return Clase.get();
	}
	
	@PutMapping("/Clases")
	public Clase updateClase(@RequestBody Clase Clase) {
		return Claserepositorio.save(Clase);
	}
	
	@DeleteMapping("/Clases/{id}")
	public void eliminar(@PathVariable Long id) {
		Clase Clase = Claserepositorio.getOne(id);
		Claserepositorio.delete(Clase);

	}
	
}