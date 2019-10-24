package com.academia.main.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.academia.main.repositorios.AsignaturaRepository;

@Controller
public class CursoControlador {

	@Autowired
	private AsignaturaRepository Asignaturapositorio;
	
	@GetMapping("/curso")
	public String buscarCursoForm() {
		return "curso";
	}

}