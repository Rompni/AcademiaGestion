package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Alumno;

public interface AlumnoServicio {
    
    void save(Alumno alumno);

    void delete(Alumno alumno);

    Alumno BuscarAlumnoPorId(Long id);

    List<Alumno> BuscarAlumnoPorNombreCurso(String nombre);

    
}