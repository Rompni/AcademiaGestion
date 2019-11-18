package com.academia.main.restcontroladores;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.academia.main.entidades.*;
import com.academia.main.servicios.*;
import org.springframework.http.HttpStatus;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import antlr.collections.List;

@RestController
@Validated
@RequestMapping("/api/v1")
public class ClaseRestControlador {

    @Autowired
    private ClaseServicio clService;
    @Autowired
    private ProfesorServicio pService;
    @Autowired
    private CursoServicio cService;
    @Autowired
    private AsignaturaServicio aService;
    @Autowired
    private HoraSemanalServicio hsService;

    private Logger LOG = Logger.getLogger(ClaseRestControlador.class);

    @GetMapping("/Clases")
    public java.util.List<Clase> getClases() {
        return clService.buscarClases();
    }

    @PostMapping("/Clases")
    @ResponseStatus(HttpStatus.CREATED)
    public Clase crearClase(@Valid @RequestBody Clase clase) throws Exception {
        java.util.List<Clase> clases = clService.buscarClases();
        LOG.info(clases);

        Asignatura asignatura = aService.BuscarAsignaturaPorId(Long.parseLong(clase.getIdAsignatura()));
        if (asignatura == null)
            throw new Exception("No se encontró la asignatura");

        Profesor profesor = pService.BuscarProfesorPorId(Long.parseLong(clase.getIdProfesor()));
        if (profesor == null)
            throw new Exception("No se encontró el profesor");

        if (clases != null) {
            for (Clase c : clases) {
                if (c.getProfesor().getId() == profesor.getId()) {
                    if (c.getAsignatura().getId() == asignatura.getId()) {
                        throw new Exception("Clase existente");
                    }
                }
            }
        }

        if (profesor.getClases() != null) {
            for (Clase clasesprofe : profesor.getClases()) {
                for (HoraSemanal horaS : clasesprofe.getHorario()) {
                    for (HoraSemanal horaN : clase.getHorario()) {
                        if (horaS.getDiaindice() == horaN.getDiaindice()
                                && horaS.getHoraindice() == horaN.getHoraindice())
                            throw new EntityNotFoundException(
                                    "El profesor tiene ocupado el día:" + horaS.getDia() + " a las " + horaS.getHora());
                    }
                }
            }
        }

        java.util.List<HoraSemanal> newhorario = new java.util.ArrayList<HoraSemanal>();
        HoraSemanal a = new HoraSemanal();

        clase.setAsignatura(asignatura);
        clase.setAlumnos(null);
        clase.setProfesor(profesor);

        java.util.List<Clase> c = Arrays.asList(clase);
        asignatura.setClases(c);
        profesor.setClases(c);

        for (HoraSemanal hs : clase.getHorario()) {

            if (hs != null) {
                hs.setClaseh(c);
                a = hsService.save(hs);
                newhorario.add(a);
            }
        }
        LOG.info(newhorario);

        return clase;
    }

    @GetMapping("/Clases/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Clase getClases(@PathVariable Long id) throws Exception {
        Clase c = clService.BuscarClasePorId(id);
        if (c == null)
            throw new EntityNotFoundException("No existe la clase");
        return c;

    }

    @GetMapping("/Clases/curso/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.List<Clase> ClasesbyCurso(@PathVariable String id) throws Exception {
        java.util.List<Clase> c = clService.BuscarClasePorCurso(id);
        if (c == null)
            throw new EntityNotFoundException("No hay clases asociadas a ese curso");
        return c;

    }

    @GetMapping("/Clases/asig/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.List<Clase> ClasesbyAsignatura(@PathVariable String id) throws Exception {
        java.util.List<Clase> c = clService.BuscarClasePorAsignatura(id);
        if (c == null)
            throw new EntityNotFoundException("No hay clases asociadas a esa asignatura");
        return c;
    }

    @GetMapping("/Clases/profe/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.List<Clase> ClasesbyProfesor(@PathVariable String id) throws Exception {
        java.util.List<Clase> c = clService.BuscarClasePorProfesor(id);
        if (c == null)
            throw new EntityNotFoundException("No hay clases asociadas a ese profesor");
        return c;
    }

    @GetMapping("/Clases/Alumno/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.List<Alumno> AlumnobyClase(@PathVariable String id) throws Exception {

        Clase clase = clService.BuscarClasePorId(Long.parseLong(id));
        if (clase == null)
            throw new EntityNotFoundException("Clase no existe");

        if (clase.getAsignatura().getCurso().getId() == null)
            throw new EntityNotFoundException("Curso no existe");

        return clService.BuscarAlumnoPorCurso(clase.getAsignatura().getCurso().getId());
    }

    @GetMapping("/Clases/CsAsPr/{idCurso}/{idAsignatura}/{idProfesor}")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.List<Clase> ClasesbyCursoAsignaturaProfesor(@PathVariable String idCurso,
            @PathVariable String idAsignatura, @PathVariable String idProfesor) {

        java.util.List<Clase> c = clService.BuscarClasePorCursoAsignaturaProfesor(idCurso, idAsignatura, idProfesor);
        if (c == null)
            throw new EntityNotFoundException("No hay clases asociadas al profesor, curso y asignatura");
        return c;
    }

    @GetMapping("/Clases/cursoprofe/{idCurso}/{idProfesor}")
    @ResponseStatus(HttpStatus.CREATED)
    public java.util.List<Clase> ClasesbyCursoProfesor(@PathVariable String idCurso, @PathVariable String idProfesor) {
        java.util.List<Clase> c = clService.BuscarClasePorCursoProfesor(idCurso, idProfesor);
        if (c == null)
            throw new EntityNotFoundException("No hay clases asociadas al profesor y a ese curso");
        return c;
    }

    @PutMapping("/Clases")
    @ResponseStatus(HttpStatus.CREATED)
    public Clase updateClase(@Valid @RequestBody Clase clase) throws Exception {

        Clase CLASE = clService.BuscarClasePorId(clase.getId());

        if (CLASE == null)
            throw new EntityNotFoundException("No se encontró la clase");
        
        clase.setIdCurso(Long.toString(CLASE.getAsignatura().getCurso().getId()));
        clase.setIdAsignatura(Long.toString(CLASE.getAsignatura().getId()));
        clase.setIdProfesor(Long.toString(CLASE.getProfesor().getId()));

        eliminar(CLASE.getId());

        clase.setId(null);
        return crearClase(clase);
    }

    @DeleteMapping("/Clases/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void eliminar(@Valid @PathVariable Long id) {
        Clase Clase = clService.BuscarClasePorId(id);
        if (Clase == null)
            throw new EntityNotFoundException("No se encontró la clase");

        clService.delete(Clase);
    }

}
