package com.academia.main.servicios;

import com.academia.main.entidades.Rol;


public interface RolServicio {

	public Rol findRolByName(String rolname);
	public Rol getRolById(long id);
	public void save(Rol rol);
}