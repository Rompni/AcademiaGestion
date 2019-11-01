package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AlumnoControlador {
	
	@GetMapping("/alumno")
	public String buscarAlumnoForm() {
		return "alumno";

	}


}
