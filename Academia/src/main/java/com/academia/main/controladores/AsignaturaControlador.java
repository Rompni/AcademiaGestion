package com.academia.main.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.academia.main.repositorios.AsignaturaRepository;


@Controller
public class AsignaturaControlador {
	
	@Autowired
	private AsignaturaRepository Asignaturapositorio;
	
	@GetMapping("/asignaturas")
	public String buscarAsignaturaForm() {
		return "asignatura";

	}
}
