package com.academia.main.restcontroladores;

import com.academia.main.entidades.Curso;
import com.academia.main.servicios.CursoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController
@RequestMapping("/api/v1")
public class CursoRestControlador {
    
    @Autowired
	private CursoServicio Cursoservicio;

	@GetMapping("/Cursos")
	public java.util.List<Curso> getCursos(){
		return Cursoservicio.buscarCursos();
	}
	
	@PostMapping("/Cursos")
	public Curso crearCurso(@RequestBody Curso Curso) {
		return Cursoservicio.save(Curso);
	}
	
	@GetMapping("/Cursos/{id}")
	public Curso getCursos(@PathVariable Long id) {
		return Cursoservicio.BuscarCursoPorId(id);	
		
	}

	@GetMapping("/Cursos/name/{nombre}")
	public java.util.List<Curso> CursosbyName(@PathVariable String nombre) {
		return Cursoservicio.BuscarCursoPorNombre(nombre);	 
	}
	
	@PutMapping("/Cursos")
	public Curso updateCurso(@RequestBody Curso Curso) {
		return Cursoservicio.save(Curso);
	}

	
	@DeleteMapping("/Cursos/{id}")
	public void eliminar(@PathVariable Long id) {
		Curso Curso = Cursoservicio.BuscarCursoPorId(id);
		Cursoservicio.delete(Curso);
	}
}