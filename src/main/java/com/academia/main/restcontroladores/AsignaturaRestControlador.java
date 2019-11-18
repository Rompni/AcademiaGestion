package com.academia.main.restcontroladores;

import java.util.List;

import javax.validation.Valid;

import com.academia.main.entidades.Asignatura;
import com.academia.main.servicios.AsignaturaServicio;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1")
public class AsignaturaRestControlador {

	@Autowired private AsignaturaServicio AsignaturaServicio;

	@GetMapping("/Asignaturas")
	public List<Asignatura> getAsignaturas(){
		return AsignaturaServicio.buscarAsignaturas();
	}
	
	@PostMapping("/Asignaturas")
	@ResponseStatus (HttpStatus.CREATED)
	public Asignatura crearAsignatura(@Valid @RequestBody Asignatura Asignatura) throws Exception {
		List<Asignatura> asignaturasDelCurso = AsignaturaServicio.BuscarAsignaturaPorCurso(Long.toString(Asignatura.getCurso().getId())); 
        for(Asignatura a: asignaturasDelCurso)
            if(a.getNombre().compareToIgnoreCase(Asignatura.getNombre()) == 0)
				throw new Exception("La Asignatura ya está registrada en este curso");
		
		Asignatura.setClases(null);
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
	@ResponseStatus (HttpStatus.CREATED)
	public Asignatura updateAsignatura(@Valid @RequestBody Asignatura Asignatura) throws Exception {
		Asignatura asignatura = AsignaturaServicio.BuscarAsignaturaPorId(Asignatura.getId());
		if(asignatura == null) throw new Exception("No se encontró la asignatura con el id="+Asignatura.getId());

		asignatura.setNombre(Asignatura.getNombre());
		asignatura.setCurso(Asignatura.getCurso());
		asignatura.setClases(Asignatura.getClases());
		return AsignaturaServicio.save(asignatura);
	}

	
	@DeleteMapping("/Asignaturas/{id}")
	@ResponseStatus (HttpStatus.CREATED)
	public void eliminar(@Valid @PathVariable Long id) throws Exception {
		Asignatura Asignatura = AsignaturaServicio.BuscarAsignaturaPorId(id);
		if(Asignatura == null) throw new Exception("No se encontró la asignatura con el id="+id);

		AsignaturaServicio.delete(Asignatura);
	}
	
}