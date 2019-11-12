package com.academia.main.servicios;

import java.util.Optional;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Curso;
import com.academia.main.repositorios.CursoRepository;


//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoServicioImpl implements CursoServicio {

    @Autowired
    private CursoRepository cursorepositorio;

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
    public List<Curso> BuscarCursoPorNombre(String nivel) {
        String[] parts = nivel.split("(?=\\s)");
        List <Curso> Cursos = cursorepositorio.findCursoByNivel(parts[0]);
        return Cursos;
    }

    @Override
    public List<Curso> buscarCursos() {
        return cursorepositorio.findAll();
    }
    
    @Override
    public Curso BuscarCursoPorNivelEtapa(String nivel, String etapa){
        return cursorepositorio.findCursoByNivelEtapa(nivel, etapa);
    }
    
}