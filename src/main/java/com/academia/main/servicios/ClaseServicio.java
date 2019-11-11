package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Clase;

public interface ClaseServicio {
    
    Clase save(Clase Clase);

    void delete(Clase Clase);

    Clase BuscarClasePorId(Long id);

    List<Clase> buscarClases();

	List<Clase> BuscarClasePorCurso(String id);


}