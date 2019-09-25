package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Alumno;
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

}
