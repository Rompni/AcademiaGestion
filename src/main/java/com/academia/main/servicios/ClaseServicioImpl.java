package com.academia.main.servicios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Curso;
import com.academia.main.repositorios.ClaseRepository;

//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClaseServicioImpl implements ClaseServicio {

    @Autowired
    private ClaseRepository Claserepositorio;

    @Autowired
    private CursoServicio cService;

    @Override
    public Clase save(Clase Clase) {
        return Claserepositorio.save(Clase);
    }

    @Override
    public void delete(Clase Clase) {
        Claserepositorio.delete(Clase);
    }

    @Override
    public Clase BuscarClasePorId(Long id) {
        Optional<Clase> Clase = Claserepositorio.findById(id);
        if (!Clase.isPresent()) {
            throw new EntityNotFoundException("No se encontro el Clase con id " + id);
        }
        return Clase.get();
    }

    @Override
    public java.util.List<Clase> buscarClases() {
        return Claserepositorio.findAll();
    }

    @Override
    public List<Clase> BuscarClasePorCurso(String id) {
        Curso curso = cService.BuscarCursoPorId(Long.parseLong(id));
        return Claserepositorio.findClasesByCurso(curso);
    }
    
}