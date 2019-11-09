package com.academia.main.entidades;

import java.io.Serializable;
import java.util.Arrays;
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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String usuario;
	
	@Column(nullable = false)
	private String clave;
	
	@Column(nullable = false)
	private Boolean habilitado;

	@ManyToMany()
    @JoinTable(name = "USUARIOS_ROLES",
    joinColumns = @JoinColumn(
    name = "ID_USER", referencedColumnName="id"),
    inverseJoinColumns = @JoinColumn(name = "ID_ROL", referencedColumnName="id"))
    private Set<Rol> roles;
	
	public Usuario() {
		super();
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
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		if(this.roles == null)
			this.roles = new HashSet<Rol>();
		this.roles.addAll(roles);
	}

	public Usuario(String usuario,String clave) {
		this.usuario = usuario;
		this.clave = clave;	
	}

	

}
