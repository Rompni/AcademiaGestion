package com.academia.main.servicios;

import com.academia.main.entidades.Rol;
import com.academia.main.entidades.Usuario;;


public interface RolServicio {

	public Usuario findUsuarioByRol(String username);
	Rol getRolById(long id);
	public void save(Rol rol);
}