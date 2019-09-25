package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

public class HoraSemanal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String dia;
	private String hora;
	private Integer diaindice;
	private Integer horaindice;
	private List<Clase> clases;
	
	public HoraSemanal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getDiaindice() {
		return diaindice;
	}

	public void setDiaindice(Integer diaindice) {
		this.diaindice = diaindice;
	}

	public Integer getHoraindice() {
		return horaindice;
	}

	public void setHoraindice(Integer horaindice) {
		this.horaindice = horaindice;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	
	
	
}
