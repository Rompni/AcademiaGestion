package com.academia.main.restcontroladores;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.academia.main.entidades.Asignatura;
import com.academia.main.entidades.Clase;
import com.academia.main.entidades.HoraSemanal;
import com.academia.main.entidades.Profesor;
import com.academia.main.servicios.AsignaturaServicio;
import com.academia.main.servicios.ClaseServicio;
//import com.academia.main.servicios.CursoServicio;
import com.academia.main.servicios.HoraSemanalServicio;
import com.academia.main.servicios.ProfesorServicio;
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
    // @Autowired
    // private CursoServicio cService;
    @Autowired
    private ProfesorServicio pService;
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
        
        Asignatura asignatura = aService.BuscarAsignaturaPorId(Long.parseLong(clase.getIdAsignatura()));
        if (asignatura == null)
            throw new EntityNotFoundException("No se encontro la asignatura");

        Profesor profesor = pService.BuscarProfesorPorId(Long.parseLong(clase.getIdProfesor()));
        if (profesor == null)
            throw new EntityNotFoundException("No se encontro el profesor");


        if (clases != null) {
            for (Clase c : clases) {
                if (c.getProfesor().getId() == profesor.getId()) {
                    if (c.getAsignatura().getId() == asignatura.getId()) {
                        throw new EntityNotFoundException("Clase existente");
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
                                    "El profesor tiene ocupado el dia:" + horaS.getDia() + " a las " + horaS.getHora());
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

    @PutMapping("/Clases")
    public Clase updateClase(@Valid @RequestBody Clase clase) {
        Asignatura asignatura = aService.BuscarAsignaturaPorId(Long.parseLong(clase.getIdAsignatura()));
        Profesor profesor = pService.BuscarProfesorPorId(Long.parseLong(clase.getIdProfesor()));
        clase.setAsignatura(asignatura);
        clase.setProfesor(profesor);

        return clService.save(clase);
    }

    @DeleteMapping("/Clases/{id}")
    public void eliminar(@Valid @PathVariable Long id) {
        Clase Clase = clService.BuscarClasePorId(id);
        clService.delete(Clase);
    }

}
