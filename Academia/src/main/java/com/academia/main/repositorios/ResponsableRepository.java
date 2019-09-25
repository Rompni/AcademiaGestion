package com.academia.main.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Responsable;
@Repository
public interface ResponsableRepository extends JpaRepository<Responsable, Long> {

}
