package com.academia.main.servicios;

import com.academia.main.entidades.Curso;
import com.academia.main.repositorios.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class CursoServicioImpl implements CursoServicio {

    @Autowired private CursoRepository Cursorepositorio;

    @Override
    public void save(Curso Curso) {
        Cursorepositorio.save(Curso);
    }

    @Override
    public void delete(Curso Curso) {
        Cursorepositorio.delete(Curso);
    }

    @Override
    public Curso BuscarCursoPorId(Long id) {
        return Cursorepositorio.getOne(id);
    }
    
}