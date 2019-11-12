package com.academia.main.restcontroladores;

import java.util.List;

import com.academia.main.entidades.Curso;
import com.academia.main.servicios.CursoServicio;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
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
	@ResponseBody
	public Curso crearCurso(@RequestBody Curso Curso) throws Exception {
		Curso curso = Cursoservicio.BuscarCursoPorNivelEtapa(Curso.getNivel(), Curso.getEtapa());
		if (curso != null)
			throw new Exception("Curso Existente");

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
	@ResponseBody
	public Curso updateCurso(@RequestBody Curso Curso) throws Exception {
		Curso curso = Cursoservicio.BuscarCursoPorId(Curso.getId());
		if (curso == null)
			throw new Exception("No se encontró el curso con id=" + Curso.getId());

		curso.setNivel(Curso.getNivel());
		curso.setEtapa(Curso.getEtapa());
		curso.setAsignaturas(Curso.getAsignaturas());
		return Cursoservicio.save(curso);
	}

	@DeleteMapping("/Cursos/{id}")
	@ResponseBody
	public void eliminar(@PathVariable Long id) throws Exception {
		Curso Curso = Cursoservicio.BuscarCursoPorId(id);
		if (Curso == null)
			throw new Exception("No se encontró el curso con id=" + id);

		Cursoservicio.delete(Curso);
	}
}