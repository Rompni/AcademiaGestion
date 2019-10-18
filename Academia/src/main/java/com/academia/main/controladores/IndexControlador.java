package com.academia.main.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class IndexControlador {
	
	@GetMapping(path = {"/", "/login"})
	public String index() {	
		return "index";
	}
	
}
