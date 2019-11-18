package com.academia.main.servicios;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Clase;
import com.academia.main.repositorios.AlumnoRepository;

//import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    private static final List<Clase> AlumnoRepository = null;
    @Autowired
    private AlumnoRepository aRepository;

    // private Logger LOG = Logger.getLogger(AlumnoServicioImpl.class);

    @Override
    public Alumno save(Alumno alumno) {
        return aRepository.save(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        aRepository.delete(alumno);
    }

    @Override
    public Alumno BuscarAlumnoPorId(Long id) {
        Optional<Alumno> alumno = aRepository.findById(id);
        if (!alumno.isPresent()) {
            throw new EntityNotFoundException("No se encontro el alumno con id " + id);
        }
        return alumno.get();
    }

    @Override
    public List<Alumno> BuscarAlumnoPorNombre(String nombre) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List<Alumno> alumnos = aRepository.findAlumnoByNombre(parts[0]);
        return alumnos;
    }

    @Override
    public List<Alumno> buscarAlumnos() {
        return aRepository.findAll();
    }

    @Override
    public List<Alumno> BuscarAlumnoPorCurso(String id) {
        return aRepository.findAlumnoByCurso(Long.parseLong(id));
    }

    @Override
    public List<Alumno> BuscarAlumnoPorCursoyNombre(String id, String Nombre) {
        return aRepository.findAlumnoByCursoAndNombre(Long.parseLong(id), Nombre);
    }

    @Override
    public Alumno BuscarAlumnoPorNif(String nif) {
        return aRepository.findAlumnoByNif(nif);
    }

    @Override
    public List<Clase> BuscarClasePorCurso(String idCurso, String idAlumno) {
        return aRepository.findClaseByCurso(Long.parseLong(idCurso),Long.parseLong(idAlumno));
    }

    
}