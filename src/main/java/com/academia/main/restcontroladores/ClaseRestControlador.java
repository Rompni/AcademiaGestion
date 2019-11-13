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
    public Clase getClases(@PathVariable Long id) {
        return clService.BuscarClasePorId(id);

    }

    @GetMapping("/Clases/curso/{id}")
    public java.util.List<Clase> ClasesbyCurso(@PathVariable String id) {
        return clService.BuscarClasePorCurso(id);
    }

    @GetMapping("/Clases/asig/{id}")
    public java.util.List<Clase> ClasesbyAsignatura(@PathVariable String id) {
        return clService.BuscarClasePorAsignatura(id);
    }

    @GetMapping("/Clases/profe/{id}")
    public java.util.List<Clase> ClasesbyProfesor(@PathVariable String id) {
        return clService.BuscarClasePorProfesor(id);
    }

    @GetMapping("/Clases/Alumno/{id}")
    public java.util.List<Alumno> AlumnobyClase(@PathVariable String id) {

        Clase clase = clService.BuscarClasePorId(Long.parseLong(id));
        if (clase == null)
            throw new EntityNotFoundException("Clase no existe");

        if (clase.getAsignatura().getCurso().getId() == null)
            throw new EntityNotFoundException("Curso no existe");

        return clService.BuscarAlumnoPorCurso(clase.getAsignatura().getCurso().getId());
    }

    @GetMapping("/Clases/CsAsPr/{idCurso}/{idAsignatura}/{idProfesor}")
    public java.util.List<Clase> ClasesbyCursoAsignaturaProfesor(@PathVariable String idCurso,
            @PathVariable String idAsignatura, @PathVariable String idProfesor) {
        return clService.BuscarClasePorCursoAsignaturaProfesor(idCurso, idAsignatura, idProfesor);
    }

    @GetMapping("/Clases/cursoprofe/{idCurso}/{idProfesor}")
    public java.util.List<Clase> ClasesbyCursoProfesor(@PathVariable String idCurso, @PathVariable String idProfesor) {
        return clService.BuscarClasePorCursoProfesor(idCurso, idProfesor);
    }

    @PutMapping("/Clases")
    @ResponseStatus(HttpStatus.CREATED)
    public Clase updateClase(@Valid @RequestBody Clase clase) throws Exception {

        java.util.List<Clase> clases = clService.buscarClases();

        if (clase.getIdCurso() == null)
            throw new Exception("El curso  es nula");

        if (clase.getIdAsignatura() == null)
            throw new Exception("La asignatura es nula");

        if (clase.getIdProfesor() == null)
            throw new Exception("el profesor es nulo");

        Clase CLASE = clService.BuscarClasePorId(clase.getId());
        if (CLASE == null)
            throw new EntityNotFoundException("No se encontró la clase");

        if (clases != null) {
            for (Clase c : clases) {
                if (c.getId() == clase.getId())
                    continue;

                if (c.getProfesor().getId() == Long.parseLong(clase.getIdProfesor())) {
                    if (c.getAsignatura().getId() == Long.parseLong(clase.getIdAsignatura())) {
                        throw new Exception("Clase existente");
                    }
                }
            }
        }

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
