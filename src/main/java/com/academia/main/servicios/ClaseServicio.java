package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Clase;

public interface ClaseServicio {
    
    Clase save(Clase Clase);

    void delete(Clase Clase);

    Clase BuscarClasePorId(Long id);

    List<Clase> buscarClases();

    List<Clase> BuscarClasePorCurso(String id);
    List<Clase> BuscarClasePorAsignatura(String id);
    List<Clase> BuscarClasePorProfesor(String id);
    List<Clase> BuscarClasePorCursoAsignaturaPrifesor(String id);


}