package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Alumno;

public interface AlumnoServicio {
    
    Alumno save(Alumno alumno);

    List<Alumno> buscarAlumnos();

    void delete(Alumno alumno);

    Alumno BuscarAlumnoPorId(Long id);

    List<Alumno> BuscarAlumnoPorNombre(String nombre);
    List<Alumno> BuscarAlumnoPorCurso(String id);
    List<Alumno> BuscarAlumnoPorCursoyNombre(String id, String Nombre);
    
}