package com.academia.main.controladores;

import javax.validation.Valid;

import com.academia.main.entidades.Usuario;
import com.academia.main.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsuarioControlador {

	@Autowired
	private UsuarioServicio usuarioService;

	@GetMapping("/home")
	public String index(Model model) {
		return "home";
	}

	@GetMapping("/registro")
	public String mostrarFormRegistro(Model model) {
		Usuario u = new Usuario();
		model.addAttribute("user", u);
		return "registro";
	}

	@PostMapping("/registro")
	public String registroUsuario(@ModelAttribute("user") @Valid Usuario usuario, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "registro";
		}
		usuarioService.save(usuario);

		model.addAttribute("user", usuario);

		return "home";
	}
	
}
