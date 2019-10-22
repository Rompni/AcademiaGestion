package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Responsable;
import com.academia.main.repositorios.ResponsableRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class ResponsableServicioImpl implements ResponsableServicio {

    @Autowired private ResponsableRepository Responsablerepositorio;

    @Override
    public void save(Responsable Responsable) {
        Responsablerepositorio.save(Responsable);
    }

    @Override
    public void delete(Responsable Responsable) {
        Responsablerepositorio.delete(Responsable);
    }

    @Override
    public Responsable BuscarResponsablePorId(Long id) {
        return Responsablerepositorio.getOne(id);
    }

    @Override
    public List<Responsable> BuscarResponsablePorNombre(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    
}