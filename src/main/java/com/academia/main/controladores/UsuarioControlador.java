package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;




@Controller
public class UsuarioControlador {

	@GetMapping("/home")
	public String InicioHome() {
		return "home";

	}
	
}
