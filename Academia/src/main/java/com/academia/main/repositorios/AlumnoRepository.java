package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.main.entidades.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}
