package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Curso;

public interface CursoServicio {
    
    Curso save(Curso Curso);

    void delete(Curso Curso);

    Curso BuscarCursoPorId(Long id);

    List<Curso> buscarCursos();

    List<Curso> BuscarCursoPorNombre(String nombre);

    Curso BuscarCursoPorNivelEtapa(String nivel, String etapa);
}