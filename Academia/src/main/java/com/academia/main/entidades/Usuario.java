package com.academia.main.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private String usuario;
	
	@Column(nullable = false)
	private String clave;
	
	@Column(nullable = false)
	private Boolean habilitado;
	
	private String role;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "USUARIOS_ROLES",
    joinColumns = @JoinColumn(
    name = "ID_USER", referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName="id"))
    private Set<Rol> roles;
	
	@OneToOne
	@JoinColumn(name = "ID_PROFESOR", referencedColumnName = "id")
	private Profesor profesor;
	
	@OneToOne
	@JoinColumn(name = "ID_ALUMNO", referencedColumnName = "id")
	private Alumno alumno;
	
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
	
	
	
	
	
	
	
}
