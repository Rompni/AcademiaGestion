package com.academia.main.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Clase implements Serializable {

	private Asignatura asignatura;
	private Profesor profesor;
	private List<Date> horario;
	
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

	public List<Date> getHorario() {
		return horario;
	}

	public void setHorario(List<Date> horario) {
		this.horario = horario;
	}
	
	
	
	
}
