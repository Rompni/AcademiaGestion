package com.academia.main.restcontroladores;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Curso;
import com.academia.main.entidades.Responsable;
import com.academia.main.servicios.AlumnoServicio;
import com.academia.main.servicios.CursoServicio;
import com.academia.main.servicios.ResponsableServicio;

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
public class AlumnoRestControlador {

	@Autowired
	private AlumnoServicio alumnoservicio;
	@Autowired
	private CursoServicio cService;
	@Autowired
	private ResponsableServicio rService;
	private String idAlumno;

	@GetMapping("/Alumnos")
	public List<Alumno> getAlumnos() {
		return alumnoservicio.buscarAlumnos();
	}

	@PostMapping("/Alumnos")
	public Alumno crearAlumno(@RequestBody Alumno alumno) {
		Alumno al = alumnoservicio.BuscarAlumnoPorNif(alumno.getNif());
		if (al != null)
			throw new EntityNotFoundException("Alumno existente");

		Curso curso = cService.BuscarCursoPorId(Long.parseLong(alumno.getStringaux()));
		List<Alumno> a = Arrays.asList(alumno);
		curso.setAlumnos(a);
		// curso = cService.save(curso);
		alumno.setCursoA(curso);

		Responsable responsable = alumno.getResponsable();
		if (responsable != null) {
			responsable.setAlumnosrespon(new LinkedList<Alumno>());
			responsable.getAlumnosrespon().add(alumno);
			responsable = rService.save(responsable);
			alumno.setResponsable(responsable);
		}

		return alumnoservicio.save(alumno);
	}

	@GetMapping("/Alumnos/{id}")
	public Alumno getAlumnos(@PathVariable Long id) {
		return alumnoservicio.BuscarAlumnoPorId(id);

	}

	@GetMapping("/Alumnos/name/{nombre}")
	public List<Alumno> AlumnosbyName(@PathVariable String nombre) {
		return alumnoservicio.BuscarAlumnoPorNombre(nombre);
	}

	@GetMapping("/Alumnos/curso/{id}")
	public List<Alumno> AlumnosbyCurso(@PathVariable String id) {
		return alumnoservicio.BuscarAlumnoPorCurso(id);
	}

	@GetMapping("/Alumnos/name/{nombre}/{curso}")
	public List<Alumno> AlumnosbyName(@PathVariable String nombre, @PathVariable String curso) {
		return alumnoservicio.BuscarAlumnoPorCursoyNombre(curso, nombre);
	}

	@PutMapping("/Alumnos")
	public Alumno updateAlumno(@RequestBody Alumno alumno) {
		Curso curso = cService.BuscarCursoPorId(Long.parseLong(alumno.getStringaux()));
		alumno.setCursoA(curso);
		return alumnoservicio.save(alumno);
	}

	@DeleteMapping("/Alumnos/{id}")
	public void eliminar(@PathVariable Long id) {
		Alumno alumno = alumnoservicio.BuscarAlumnoPorId(id);
		alumnoservicio.delete(alumno);
	}

	@GetMapping("/Alumnos/clase/{idAlumno}")
	public List<Clase> ClasebyCurso(@PathVariable String idAlumno) {
		Alumno alumno = alumnoservicio.BuscarAlumnoPorId(Long.parseLong(idAlumno));
		if (alumno == null)
			throw new EntityNotFoundException("Alumno no existe");

		Curso curso = cService.BuscarCursoPorId(alumno.getCursoA().getId());
		if (curso == null)
			throw new EntityNotFoundException("Curso no existe");

		return alumnoservicio.BuscarClasePorCurso(Long.toString(curso.getId()), idAlumno);
	}

}
