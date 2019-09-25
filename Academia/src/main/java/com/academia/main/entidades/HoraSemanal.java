package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class HoraSemanal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String dia;
	
	@Column(nullable = false)
	private String hora;
	
	@Column(nullable = false)
	private Integer diaindice;
	
	@Column(nullable = false)
	private Integer horaindice;
	
	//no se si falte
	private List<Clase> clases;
	
	public HoraSemanal() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
