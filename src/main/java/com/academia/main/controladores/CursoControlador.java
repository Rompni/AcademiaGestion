package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CursoControlador {

	
	@GetMapping("/curso")
	public String buscarCursoForm() {
		return "curso";
	}

}