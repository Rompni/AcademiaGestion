package com.academia.main.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.academia.main.repositorios.AlumnoRepository;


@Controller
public class AlumnoControlador {
	
	@Autowired
	private AlumnoRepository alumnorepositorio;
	
	@GetMapping("/alumno")
	public String buscarAlumnoForm() {
		return "alumno";

	}


}
