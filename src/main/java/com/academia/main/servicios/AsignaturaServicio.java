package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Asignatura;

public interface AsignaturaServicio {
    
    Asignatura save(Asignatura Asignatura);

    List<Asignatura> buscarAsignaturas();

    void delete(Asignatura Asignatura);

    Asignatura BuscarAsignaturaPorId(Long id);

    List<Asignatura> BuscarAsignaturaPorNombre(String nombre);

    List<Asignatura> BuscarAsignaturaPorCurso(String id);

    
}