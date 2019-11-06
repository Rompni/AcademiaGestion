package com.academia.main.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academia.main.entidades.*;
import com.academia.main.repositorios.*;


@Service
public class RolServicioImpl implements RolServicio {
	@Autowired
	private RolRepository rolRepositorio;

    @Override
    public Usuario findUsuarioByRol(String username) {
       return null;
    }

    @Override
    public Rol getRolById(long id) {
        return rolRepositorio.getOne(id);
    }

    @Override
    public void save(Rol rol) {
        rolRepositorio.save(rol);

    }

	

}