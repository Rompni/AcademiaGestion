package com.academia.main.entidades;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CURSOS")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nivel;
	
	@Column(nullable = false)
	private String etapa;
	
	@OneToMany(mappedBy = "cursoA", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JsonIgnore
	private List<Alumno> alumnos;

	@OneToMany(mappedBy = "curso", cascade = { CascadeType.ALL }, orphanRemoval = true)
	@JsonIgnore
	private List<Asignatura> asignaturas;
	
	public Curso() {
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
		this.alumnos.addAll(alumnos);
		for(Alumno alumno: alumnos)
			alumno.setCursoA(this);
	}

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		if(this.asignaturas == null){
		this.asignaturas = new LinkedList<Asignatura>();
			this.asignaturas.clear();
		}

		if(asignaturas == null)
			return;

		this.asignaturas.addAll(asignaturas);
		for(Asignatura asignatura: asignaturas)
			asignatura.setCurso(this);
		
		
	}


	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getEtapa() {
		return etapa;
	}

	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Curso(String nivel, String etapa) {
		this.nivel = nivel;
		this.etapa = etapa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alumnos == null) ? 0 : alumnos.hashCode());
		result = prime * result + ((asignaturas == null) ? 0 : asignaturas.hashCode());
		result = prime * result + ((etapa == null) ? 0 : etapa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		if (alumnos == null) {
			if (other.alumnos != null)
				return false;
		} else if (!alumnos.equals(other.alumnos))
			return false;
		if (asignaturas == null) {
			if (other.asignaturas != null)
				return false;
		} else if (!asignaturas.equals(other.asignaturas))
			return false;
		if (etapa == null) {
			if (other.etapa != null)
				return false;
		} else if (!etapa.equals(other.etapa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		return true;
	}
	
	
}
