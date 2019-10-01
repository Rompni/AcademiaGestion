package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "cursos")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer nivel;
	
	@Column(nullable = false)
	private String etapa;
	
	@OneToMany(mappedBy = "curso")
	private List<Alumno> alumnos;
	
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
