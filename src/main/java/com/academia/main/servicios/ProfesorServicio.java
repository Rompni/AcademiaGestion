package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Profesor;

public interface ProfesorServicio {
    
    Profesor save(Profesor profesor);

    List<Profesor> buscarProfesores();

    void delete(Profesor profesor);

    Profesor BuscarProfesorPorId(Long id);

    List<Profesor> BuscarProfesorPorNombre(String nombre, String nif);
    
}