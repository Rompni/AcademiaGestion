package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.academia.main.entidades.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

}
