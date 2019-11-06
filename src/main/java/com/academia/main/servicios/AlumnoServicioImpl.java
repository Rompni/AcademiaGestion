package com.academia.main.servicios;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Alumno;
import com.academia.main.entidades.Curso;
import com.academia.main.entidades.Responsable;
import com.academia.main.repositorios.AlumnoRepository;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    @Autowired private AlumnoRepository aRepository;
    @Autowired private CursoServicio cService;
    @Autowired private ResponsableServicio rService;

    private Logger LOG = Logger.getLogger(AlumnoServicioImpl.class);

    @Override
    public Alumno save(Alumno alumno) {
        
        Curso curso = cService.BuscarCursoPorId(Long.parseLong(alumno.getStringaux()));
        curso.getAlumnos().add(alumno);
        curso = cService.save(curso);
        alumno.setCursoA(curso);

        Responsable responsable = alumno.getResponsable();
        if(responsable != null) {
        responsable.setAlumnosrespon(new LinkedList<Alumno>());
        responsable.getAlumnosrespon().add(alumno);
        responsable = rService.save(responsable);
        alumno.setResponsable(responsable);
        }
        return aRepository.save(alumno);
    }

    @Override
    public void delete(Alumno alumno) {
        aRepository.delete(alumno);
    }

    @Override
    public Alumno BuscarAlumnoPorId(Long id) {
        Optional<Alumno> alumno = aRepository.findById(id);
        if(!alumno.isPresent()){
            throw new EntityNotFoundException("No se encontro el alumno con id "+id);
        }
        return alumno.get();
        }

    @Override
    public List<Alumno> BuscarAlumnoPorNombre(String nombre) {
        String[] parts = nombre.split("(?=\\s)");
        System.out.println(parts[0]);
        List <Alumno> alumnos = aRepository.findAlumnoByNombre(parts[0]);
        return alumnos;
    }

    @Override
    public List<Alumno> buscarAlumnos() {
        return aRepository.findAll();
    }

    
}