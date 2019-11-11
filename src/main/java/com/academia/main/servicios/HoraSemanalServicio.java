package com.academia.main.servicios;

import java.util.List;

import com.academia.main.entidades.HoraSemanal;

public interface HoraSemanalServicio {
    
    HoraSemanal save(HoraSemanal HoraSemanal);

    void delete(HoraSemanal HoraSemanal);

    HoraSemanal BuscarHoraSemanalPorId(Long id);

    List<HoraSemanal> buscarHoraSemanals();

}