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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "RESPONSABLES")
public class Responsable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String apellido1;

	private String apellido2;

	@Column(nullable = false)
	private String nif;

	@Column(nullable = false)
	private String telefono;

	@Column(nullable = false)
	private String correo;

	@OneToMany(mappedBy = "responsable", cascade = { CascadeType.ALL })
	@JsonIgnore
	private List<Alumno> alumnosrespon;

	public Responsable(){}

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Alumno> getAlumnosrespon() {
		return alumnosrespon;
	}

	public void setAlumnosrespon(List<Alumno> alumnosrespon) {
			if (this.alumnosrespon == null) {
				this.alumnosrespon = new LinkedList<Alumno>();
				this.alumnosrespon.clear();
			}
	
			if (alumnosrespon  == null)
				return;
	
			this.alumnosrespon.addAll(alumnosrespon );
			for (Alumno al : alumnosrespon)
				al.setResponsable(this);
	}
	
}
