package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.main.entidades.Profesor;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}
