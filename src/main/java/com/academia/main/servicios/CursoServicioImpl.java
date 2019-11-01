package com.academia.main.servicios;

import java.util.Optional;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Curso;
import com.academia.main.repositorios.CursoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServicioImpl implements CursoServicio {

    @Autowired private CursoRepository cursorepositorio;

    @Override
    public Curso save(Curso Curso) {
       return cursorepositorio.save(Curso);
    }

    @Override
    public void delete(Curso Curso) {
        cursorepositorio.delete(Curso);
    }

    @Override
    public Curso BuscarCursoPorId(Long id) {
        Optional<Curso> curso = cursorepositorio.findById(id);
        if(!curso.isPresent()){
            throw new EntityNotFoundException("No se encontro el Curso con id "+id);
        }
        return curso.get();
    }

    
    @Override
    public List<Curso> BuscarCursoPorNombre(String nombre) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List <Curso> Cursos = cursorepositorio.findCursoByNombre(parts[0]);
        return Cursos;
    }

    @Override
    public List<Curso> buscarCursos() {
        return cursorepositorio.findAll();
    }
    
}