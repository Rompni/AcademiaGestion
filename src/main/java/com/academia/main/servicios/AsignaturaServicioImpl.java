package com.academia.main.servicios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Asignatura;
import com.academia.main.entidades.Curso;
import com.academia.main.repositorios.AsignaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsignaturaServicioImpl implements AsignaturaServicio {

    @Autowired private AsignaturaRepository Asignaturarepositorio;

    @Autowired private CursoServicio cService;

    @Override
    public Asignatura save(Asignatura asignatura) {
        return Asignaturarepositorio.save(asignatura);
    }

    @Override
    public void delete(Asignatura Asignatura) {
        Asignaturarepositorio.delete(Asignatura);
    }

    @Override
    public Asignatura BuscarAsignaturaPorId(Long id) {
        Optional<Asignatura> Asignatura = Asignaturarepositorio.findById(id);
        if(!Asignatura.isPresent()){
            throw new EntityNotFoundException("No se encontro el Asignatura con id "+id);
        }
        return Asignatura.get();
        }

    @Override
    public List<Asignatura> BuscarAsignaturaPorNombre(String nombre) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List <Asignatura> Asignaturas = Asignaturarepositorio.findAsignaturaByNombre(parts[0]);
        return Asignaturas;
    }

    @Override
    public List<Asignatura> buscarAsignaturas() {
        return Asignaturarepositorio.findAll();
    }
    
    @Override
    public List<Asignatura> BuscarAsignaturaPorCurso(String id) {
        Curso curso = cService.BuscarCursoPorId(Long.parseLong(id));
        return curso.getAsignaturas();
    }

    
}