package com.academia.main.entidades;

import java.io.Serializable;

public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer nivel;
	private String etapa;
	
	public Curso() {
	}

	public Curso(Integer nivel, String etapa) {
		super();
		this.nivel = nivel;
		this.etapa = etapa;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	
}
