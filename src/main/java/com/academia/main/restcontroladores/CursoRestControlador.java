package com.academia.main.restcontroladores;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.academia.main.entidades.Curso;
import com.academia.main.servicios.CursoServicio;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1")
public class CursoRestControlador {

	@Autowired
	private CursoServicio Cursoservicio;

	private Logger LOG = Logger.getLogger(CursoRestControlador.class);

	@GetMapping("/Cursos")
	public List<Curso> getCursos() {
		return Cursoservicio.buscarCursos();
	}

	@PostMapping("/Cursos")
	@ResponseStatus(HttpStatus.CREATED)
	public Curso crearCurso(@Valid @RequestBody Curso Curso) throws Exception {
		
		Curso curso = Cursoservicio.BuscarCursoPorNivelEtapa(Curso.getNivel(), Curso.getEtapa());
		if (curso != null)
			throw new EntityNotFoundException("curso existente");

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
	@ResponseStatus(HttpStatus.CREATED)
	public Curso updateCurso(@Valid @RequestBody Curso Curso) throws Exception {
		Curso curso = Cursoservicio.BuscarCursoPorId(Curso.getId());
		if (curso == null)
			throw new Exception("No se encontró el curso con id=" + Curso.getId());

		curso.setNivel(Curso.getNivel());
		curso.setEtapa(Curso.getEtapa());
		curso.setAsignaturas(Curso.getAsignaturas());
		return Cursoservicio.save(curso);
	}

	@DeleteMapping("/Cursos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public void eliminar(@Valid @PathVariable Long id) throws Exception {
		Curso Curso = Cursoservicio.BuscarCursoPorId(id);
		if (Curso == null)
			throw new Exception("No se encontró el curso con id=" + id);

		Cursoservicio.delete(Curso);
	}
}