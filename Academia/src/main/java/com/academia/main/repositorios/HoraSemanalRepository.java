package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.HoraSemanal;
@Repository
public interface HoraSemanalRepository extends JpaRepository<HoraSemanal, Long> {

}
