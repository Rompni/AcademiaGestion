package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Curso;

import org.springframework.http.ResponseEntity;

public interface CursoServicio {
    
    Curso save(Curso Curso);

    void delete(Curso Curso);

    Curso BuscarCursoPorId(Long id);

    List<Curso> buscarCursos();

    List<Curso> BuscarCursoPorNombre(String nombre);

    Curso BuscarCursoPorNivelEtapa(String nivel, String etapa);
}