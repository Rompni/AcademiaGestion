package com.academia.main.servicios;


import com.academia.main.entidades.Curso;

public interface CursoServicio {
    
    void save(Curso Curso);

    void delete(Curso Curso);

    Curso BuscarCursoPorId(Long id);

}