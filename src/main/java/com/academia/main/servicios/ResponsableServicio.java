package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Responsable;

public interface ResponsableServicio {
    
    Responsable save(Responsable Responsable);

    List<Responsable> buscarResponsables();

    void delete(Responsable Responsable);

    Responsable BuscarResponsablePorId(Long id);

    List<Responsable> BuscarResponsablePorNombre(String nombre);
}