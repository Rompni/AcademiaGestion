package com.academia.main.entidades;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "asignaturas")
public class Asignatura implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "ID_CURSO")
	private Curso curso;	
	
	@OneToMany(mappedBy = "asignatura" )
	@JsonIgnore
	private List<Clase> clases;

	public Asignatura() {
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso cursoa) {
		this.curso = cursoa;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
	if(this.clases == null){
		this.clases = new LinkedList<Clase>();
			this.clases.clear();
		}

		if(clases == null)
			return;

		this.clases.addAll(clases);
		for(Clase clase: clases)
			clase.setAsignatura(this);
	}
	
}
