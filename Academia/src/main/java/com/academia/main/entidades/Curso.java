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
@Table(name = "CURSOS")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long nivel;
	
	@Column(nullable = false)
	private String etapa;
	
	@OneToMany(mappedBy = "curso")
	private List<Alumno> alumnos;
	
	@OneToMany(mappedBy = "cursoa")
	private List<Asignatura> asignaturas;
	
	public Curso() {
		super();
	}
	

	public Curso(Long nivel, String etapa) {
		super();
		this.nivel = nivel;
		this.etapa = etapa;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public Long getNivel() {
		return nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	
}
