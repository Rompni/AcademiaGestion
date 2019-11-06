package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@Entity
@Table(name = "HORAS_SEMANALES")
public class HoraSemanal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String dia;
	
	@Column(nullable = false)
	private String hora;
	
	@Column()//nullable = false
	private Long diaindice;
	
	@Column()//nullable = false
	private Long horaindice;
	
	@ManyToMany
	@JoinTable(name= "CLASE_HORA",
	joinColumns= @JoinColumn(
	name="ID_HORASEMANAL", referencedColumnName="id"),
	inverseJoinColumns=@JoinColumn(
	name="ID_CLASE",referencedColumnName="id"))
	private List<Clase> claseh;
	
	public HoraSemanal() {
		super();
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

	public Long getDiaindice() {
		return diaindice;
	}

	public void setDiaindice(Long diaindice) {
		this.diaindice = diaindice;
	}

	public Long getHoraindice() {
		return horaindice;
	}

	public void setHoraindice(Long horaindice) {
		this.horaindice = horaindice;
	}

	public List<Clase> getClaseh() {
		return claseh;
	}

	public void setClaseh(List<Clase> claseh) {
		this.claseh = claseh;
	}
	
	

	
	
	
}
