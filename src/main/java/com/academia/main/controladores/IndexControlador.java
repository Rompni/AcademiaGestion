package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexControlador {
	
	@GetMapping(path = {"/", "/login"})
	public String login() {	
		return "login";
	}
	
}
