package com.academia.main.controlladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.academia.main.repositorios.UsuarioRepository;



@Controller
public class UsuarioControlador {
	
	@Autowired
	private UsuarioRepository usuariopositorio;
	
	@GetMapping("/home")
	public String buscarUsiarioForm() {
		return "home";

	}
}
