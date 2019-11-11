package com.academia.main.servicios;

import java.util.Optional;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityNotFoundException;


import com.academia.main.entidades.HoraSemanal;
import com.academia.main.repositorios.HoraSemanalRepository;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HoraSemanalServicioImpl implements HoraSemanalServicio {

    @Autowired
    private HoraSemanalRepository HoraSemanalrepositorio;
    private Logger LOG = Logger.getLogger(HoraSemanalServicioImpl.class);

    @Override
    public HoraSemanal save(HoraSemanal HoraSemanal) {
        return HoraSemanalrepositorio.save(HoraSemanal);
    }

    @Override
    public void delete(HoraSemanal HoraSemanal) {
        HoraSemanalrepositorio.delete(HoraSemanal);
    }

    @Override
    public HoraSemanal BuscarHoraSemanalPorId(Long id) {
        Optional<HoraSemanal> HoraSemanal = HoraSemanalrepositorio.findById(id);
        if (!HoraSemanal.isPresent()) {
            throw new EntityNotFoundException("No se encontro la HoraSemanal con id " + id);
        }
        return HoraSemanal.get();
    }

    @Override
    public List<HoraSemanal> buscarHoraSemanals() {
        return HoraSemanalrepositorio.findAll();
    }

    
}