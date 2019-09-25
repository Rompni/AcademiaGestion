package com.academia.main.entidades;

import java.io.Serializable;

public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Curso curso;
	private String nombre;
	
	public Asignatura() {
	}

	public Asignatura(Curso curso, String nombre) {
		super();
		this.curso = curso;
		this.nombre = nombre;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
