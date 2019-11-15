package com.academia.main.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "ALUMNOS")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Pattern(message = " Nombre invalido ", regexp = "^[A-Za-z]+$")
	private String nombre;

	@Column(nullable = false)
	@Pattern(message = " Primer Apellido invalido ", regexp = "^[A-Za-z]+$")
	private String apellido1;

	@Pattern(message = " Segundo Apellido invalido ", regexp = "^[A-Za-z]+$")
	@Column(nullable = true)
	private String apellido2;

	@Column(unique = true, nullable = false)
	private String nif;

	@Column(nullable = false)
	@Pattern(message = " Telefono invalido[3xxxxx] ", regexp = "^3[0-9]{5}$")
	private String telefono;

	@Column(nullable = false)
	@Pattern(message = " Correo invalido ", regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
	private String correo;

	@Column
	private Boolean repetidor;

	@Column
	@Temporal(TemporalType.DATE)
	private Date fechaalta;

	@Temporal(TemporalType.DATE)
	private Date fechabaja;

	private String observaciones;

	@Transient
	private String stringaux;

	@ManyToOne
	@JoinColumn(name = "ID_CURSOA")
	private Curso cursoA;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "CLASE_ALUMNO", joinColumns = @JoinColumn(name = "ID_ALUMNO", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "ID_CLASE", referencedColumnName = "id"))
	private List<Clase> clases;

	@ManyToOne
	@JoinColumn(name = "ID_RESPONSABLE")
	private Responsable responsable;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Curso getCursoA() {
		return cursoA;
	}

	public void setCursoA(Curso cursoA) {
		this.cursoA = cursoA;
	}

	public Boolean getRepetidor() {
		return repetidor;
	}

	public void setRepetidor(Boolean repetidor) {
		this.repetidor = repetidor;
	}

	public Date getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(Date fechaalta) {
		this.fechaalta = fechaalta;
	}

	public Date getFechabaja() {
		return fechabaja;
	}

	public void setFechabaja(Date fechabaja) {
		this.fechabaja = fechabaja;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public Alumno() {
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStringaux() {
		return stringaux;
	}

	public void setStringaux(String stringaux) {
		this.stringaux = stringaux;
	}

	@Override
	public String toString() {
		return "Alumno: "+ nif;
	}

	

}
