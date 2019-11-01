package com.academia.main.servicios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Alumno;
import com.academia.main.repositorios.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    @Autowired private AlumnoRepository alumnorepositorio;

    @Override
    public Alumno save(Alumno alumno) {
        return alumnorepositorio.save(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        alumnorepositorio.delete(alumno);
    }

    @Override
    public Alumno BuscarAlumnoPorId(Long id) {
        Optional<Alumno> alumno = alumnorepositorio.findById(id);
        if(!alumno.isPresent()){
            throw new EntityNotFoundException("No se encontro el alumno con id "+id);
        }
        return alumno.get();
        }

    @Override
    public List<Alumno> BuscarAlumnoPorNombre(String nombre) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List <Alumno> alumnos = alumnorepositorio.findAlumnoByNombre(parts[0]);
        return alumnos;
    }

    @Override
    public List<Alumno> buscarAlumnos() {
        return alumnorepositorio.findAll();
    }

    
}