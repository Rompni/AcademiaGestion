package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Profesor;
@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {

}
