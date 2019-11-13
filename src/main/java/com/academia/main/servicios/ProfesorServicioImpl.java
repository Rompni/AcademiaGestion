package com.academia.main.servicios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Profesor;
import com.academia.main.repositorios.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesorServicioImpl implements ProfesorServicio {

    @Autowired
    private ProfesorRepository profesorrepositorio;

    @Override
    public Profesor save(Profesor profesor) {
        return profesorrepositorio.save(profesor);
    }

    @Override
    public void delete(Profesor profesor) {
        profesorrepositorio.delete(profesor);
    }

    @Override
    public Profesor BuscarProfesorPorId(Long id) {
        Optional<Profesor> profesor = profesorrepositorio.findById(id);
        if (!profesor.isPresent()) {
            throw new EntityNotFoundException("No se encontro el profesor con id " + id);
        }
        return profesor.get();
    }

    @Override
    public List<Profesor> BuscarProfesorPorNombre(String nombre, String nif) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List<Profesor> profesors = profesorrepositorio.findProfesorByNombreOrNif(parts[0], nif);
        return profesors;
    }

    @Override
    public List<Profesor> buscarProfesores() {
        return profesorrepositorio.findAll();
    }

    @Override
    public Profesor BuscarProfesorPorNif(String nif) {
        return profesorrepositorio.findByNif(nif);
    }

    @Override
    public List<Clase> BuscarClasesPorProfesor(Long idProfesor) {
        return profesorrepositorio.findClasesByProfesor(idProfesor);
    }

    
}