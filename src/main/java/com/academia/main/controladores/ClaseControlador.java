package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClaseControlador {

	@GetMapping("/clase")
	public String buscarClaseForm() {
		return "clase";
	}
}
