package com.academia.main;

import javax.annotation.PostConstruct;

import com.academia.main.entidades.Rol;
import com.academia.main.entidades.Usuario;
import com.academia.main.servicios.CursoServicio;
import com.academia.main.servicios.RolServicio;
import com.academia.main.servicios.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AcademiaApplication {

	@Autowired public RolServicio rolservicio;

	@Autowired public UsuarioServicio usuarioservicio;

	public static void main(String[] args) {
		SpringApplication.run(AcademiaApplication.class, args);
	}

	@PostConstruct
	public void Inicializar(){
		rolservicio.save(new Rol((long) 1, "ADMIN"));
		rolservicio.save(new Rol((long) 2,"ALUMNO"));
		rolservicio.save(new Rol((long) 3,"PROFESOR"));
		usuarioservicio.save(new Usuario("admin", "ADMIN", "admin"));

	}

}
