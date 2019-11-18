package com.academia.main.servicios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Responsable;
import com.academia.main.repositorios.ResponsableRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponsableServicioImpl implements ResponsableServicio {

    @Autowired
    private ResponsableRepository responsablerepositorio;

    @Override
    public Responsable save(Responsable Responsable) {
        return responsablerepositorio.save(Responsable);   
    }

    @Override
    public void delete(Responsable Responsable) {
        responsablerepositorio.delete(Responsable);
    }

    @Override
    public Responsable BuscarResponsablePorId(Long id) {
        Optional<Responsable> responsable = responsablerepositorio.findById(id);
        if (!responsable.isPresent()) {
            throw new EntityNotFoundException("No se encontro el responsable con id " + id);
        }
        return responsable.get();
    }

    @Override
    public List<Responsable> BuscarResponsablePorNombre(String nombre) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List <Responsable> responsables = responsablerepositorio.findResponsablebyNombre(parts[0]);
        return responsables;
    }

    @Override
    public List<Responsable> buscarResponsables() {
        return responsablerepositorio.findAll();
    }

    @Override
    public List<Alumno> getAlumnosResponsable(Long id) {
        Responsable responsable = BuscarResponsablePorId(id);
        return responsable.getAlumnosrespon();
    }

    @Override
    public Responsable BuscarResponsablePorNif(String nif) {
        return responsablerepositorio.findByNif(nif);
    }

    
}