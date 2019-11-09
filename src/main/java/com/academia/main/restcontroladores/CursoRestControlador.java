package com.academia.main.restcontroladores;

import java.util.List;

import com.academia.main.entidades.Curso;
import com.academia.main.servicios.CursoServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CursoRestControlador {
    
    @Autowired
	private CursoServicio Cursoservicio;

	@GetMapping("/Cursos")
	public List<Curso> getCursos(){
		return Cursoservicio.buscarCursos();
	}
	
	@PostMapping("/Cursos")
	public Curso crearCurso(@RequestBody Curso Curso) {
		Curso curso = Cursoservicio.BuscarCursoPorNivelEtapa(Curso.getNivel(), Curso.getEtapa());
		if(curso != null)
			return null;

		Curso.setAsignaturas(null);
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
		Curso curso = Cursoservicio.BuscarCursoPorId(Curso.getId());
		curso.setNivel(Curso.getNivel());
		curso.setEtapa(Curso.getEtapa());
		curso.setAsignaturas(Curso.getAsignaturas());
		return Cursoservicio.save(curso);
	}

	
	@DeleteMapping("/Cursos/{id}")
	public void eliminar(@PathVariable Long id) {
		Curso Curso = Cursoservicio.BuscarCursoPorId(id);
		Cursoservicio.delete(Curso);
	}
}