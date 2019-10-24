package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "CLASES")
public class Clase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_ASIGNATURA")
	private Asignatura asignatura;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;
	
	@ManyToMany(mappedBy="claseh")
	private List<HoraSemanal> horario;
	
	@ManyToMany(mappedBy="clases") 
	private List<Alumno> alumnos; 
	
	public Clase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public List<HoraSemanal> getHorario() {
		return horario;
	}

	public void setHorario(List<HoraSemanal> horario) {
		this.horario = horario;
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
	
	
	
	
	
	
	
}
