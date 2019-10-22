package com.academia.main.restcontroladores;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Curso;
import com.academia.main.repositorios.CursoRepository;

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
public class CursoRestControlador {

	@Autowired
	private CursoRepository Cursorepositorio;

	@GetMapping("/Cursos")
	public List<Curso> getCursos(){
		return Cursorepositorio.findAll();
	}
	
	@PostMapping("/Cursos")
	public Curso crearCurso(@RequestBody Curso Curso) {
		return Cursorepositorio.save(Curso);
	}
	
	@GetMapping("/Cursos/{id}")
	public Curso getCurso(@PathVariable Long id) {
		Optional<Curso> Curso = Cursorepositorio.findById(id);
		if(!Curso.isPresent()) {
			throw new EntityNotFoundException("No se encontro el Curso con id "+id);
		}
				
		return Curso.get();
	}
	
	@PutMapping("/Cursos")
	public Curso updateCurso(@RequestBody Curso Curso) {
		return Cursorepositorio.save(Curso);
	}
	
	@DeleteMapping("/Cursos/{id}")
	public void eliminar(@PathVariable Long id) {
		Curso Curso = Cursorepositorio.getOne(id);
		Cursorepositorio.delete(Curso);

	}
	
}