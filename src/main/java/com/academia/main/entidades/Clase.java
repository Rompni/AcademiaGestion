package com.academia.main.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLASE")
public class Clase implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

	@ManyToOne
	@JoinColumn(name = "ID_ASIGNATURA")
	private Asignatura asignatura;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROFESOR")
	private Profesor profesor;
	
	@ManyToMany(mappedBy="claseh", cascade = {CascadeType.ALL})
	private List<HoraSemanal> horario;
	
	@ManyToMany(mappedBy="clases" )
	@JsonIgnore
	private List<Alumno> alumnos; 

	@Transient
	private String idAsignatura;

	@Transient
	private String idProfesor;
	
	@Transient
	private String idCurso;
	
	public Clase() {
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

		public void AddHoraSemanal(HoraSemanal HS){
		//prevent endless loop
		if (horario.contains(HS))
		return ;
		//add new hora semanal
		horario.add(HS);
		//asigna si mismo a la clase
		HS.addClass(this);

		}

		  /**
   * elimina una horasemanal del horario
   * . The method keeps relationships consistency:
   * * this person is removed from the account followers 
   *   also at the twitter side
   */
  public void RemoveHoraSemanal(HoraSemanal HS) {
    //prevent endless loop
    if (!horario.contains(HS))
      return ;
    //remove horasemanal
    horario.remove(HS);
    //remove clase dela horasemanal
    HS.RemoveClass(this);
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
		this.alumnos = alumnos;
	}

	public String getIdAsignatura() {
		return idAsignatura;
	}

	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public String getIdProfesor() {
		return idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	


	@Override
	public String toString() {
		return "Clase [alumnos=" + alumnos + ", asignatura=" + asignatura + ", horario=" + horario + ", id=" + id
				+ ", profesor=" + profesor + "]";
	}
	
}
