package com.academia.main.repositorios;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.main.entidades.Curso;
import com.academia.main.entidades.HoraSemanal;
import com.academia.main.entidades.Profesor;
@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
