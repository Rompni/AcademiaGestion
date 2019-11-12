package com.academia.main.restcontroladores;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;

import com.academia.main.entidades.Asignatura;
import com.academia.main.entidades.Clase;
import com.academia.main.entidades.Curso;
import com.academia.main.entidades.HoraSemanal;
import com.academia.main.entidades.Profesor;
import com.academia.main.servicios.AsignaturaServicio;
import com.academia.main.servicios.ClaseServicio;
import com.academia.main.servicios.CursoServicio;
import com.academia.main.servicios.HoraSemanalServicio;
import com.academia.main.servicios.ProfesorServicio;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import antlr.collections.List;

@RestController
@RequestMapping("/api/v1")
public class ClaseRestControlador {

    @Autowired
    private ClaseServicio clService;
    @Autowired
    private CursoServicio cService;
    @Autowired
    private ProfesorServicio pService;
    @Autowired
    private AsignaturaServicio aService;
    @Autowired
    private HoraSemanalServicio hsService;

    private Logger LOG = Logger.getLogger(ClaseRestControlador.class);
    private Logger LOGG = Logger.getLogger(HoraSemanal.class);

    @GetMapping("/Clases")
    public java.util.List<Clase> getClases() {
        return clService.buscarClases();
    }

    @PostMapping("/Clases")
    public Clase crearClase(@RequestBody Clase clase) {
        java.util.List<Clase> clases = clService.buscarClases();
        if (clases.contains(clase))
            throw new EntityNotFoundException("Clase existente");


        Asignatura asignatura = aService.BuscarAsignaturaPorId(Long.parseLong(clase.getIdAsignatura()));
        Profesor profesor = pService.BuscarProfesorPorId(Long.parseLong(clase.getIdProfesor()));
        java.util.List<HoraSemanal> newhorario = new java.util.ArrayList<HoraSemanal>();
        HoraSemanal a = new HoraSemanal();
         

        clase.setAsignatura(asignatura);
        clase.setAlumnos(null);
        clase.setProfesor(profesor);     


        java.util.List<Clase> c = Arrays.asList(clase);
        asignatura.setClases(c);
        profesor.setClases(c);

        for(HoraSemanal hs: clase.getHorario()){
            
            if(hs != null){
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
    public Clase updateClase(@RequestBody Clase clase) {
        Asignatura asignatura = aService.BuscarAsignaturaPorId(Long.parseLong(clase.getIdAsignatura()));
        Profesor profesor = pService.BuscarProfesorPorId(Long.parseLong(clase.getIdProfesor()));
        clase.setAsignatura(asignatura);
        clase.setProfesor(profesor);
        
        return clService.save(clase);
    }

    @DeleteMapping("/Clases/{id}")
    public void eliminar(@PathVariable Long id) {
        Clase Clase = clService.BuscarClasePorId(id);
        clService.delete(Clase);
    }

}
