package com.academia.main.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.academia.main.repositorios.AlumnoRepository;


@Controller
public class AlumnoControlador {
	
	@Autowired
	private AlumnoRepository alumnorepositorio;
	
	@GetMapping("/alumnos")
	public String buscarAlumnoForm() {
		return "alumno";

	}


}
