package com.academia.main;

import javax.annotation.PostConstruct;

import com.academia.main.servicios.RolServicio;
import com.academia.main.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademiaApplication {
	
	// @Autowired
	// private RolServicio rolservicio;

	// @Autowired
	// private UsuarioServicio usuarioservicio;
 
	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@PostConstruct
	public void Inicializar(){

	}
}
