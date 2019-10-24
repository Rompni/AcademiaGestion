package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.Alumno;
import com.academia.main.repositorios.AlumnoRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class AlumnoServicioImpl implements AlumnoServicio {

    @Autowired private AlumnoRepository alumnorepositorio;

    @Override
    public void save(Alumno alumno) {
        alumnorepositorio.save(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        alumnorepositorio.delete(alumno);
    }

    @Override
    public Alumno BuscarAlumnoPorId(Long id) {
        return alumnorepositorio.getOne(id);
    }

    @Override
    public List<Alumno> BuscarAlumnoPorNombreCurso(String nombre) {
        // TODO Auto-generated method stub
        return null;
    }

    
}