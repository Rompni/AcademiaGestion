package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Clase;
@Repository
public interface ClaseRepository extends JpaRepository<Clase, Long> {

}
