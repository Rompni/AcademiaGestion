package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Clase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//falta relacion
	private Asignatura asignatura;
	
	//falta relacion
	private Profesor profesor;
	
	//falta algo aqui
	private List<HoraSemanal> horario;
	
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
	
	
	
	
}
