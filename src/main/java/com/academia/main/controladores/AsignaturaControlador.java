package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AsignaturaControlador {
	
	@GetMapping("/asignatura")
	public String buscarAsignaturaForm() {
		return "asignatura";

	}
}
