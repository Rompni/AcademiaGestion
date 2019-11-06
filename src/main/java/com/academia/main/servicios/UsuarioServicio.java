package com.academia.main.servicios;

import com.academia.main.entidades.Usuario;;


public interface UsuarioServicio {

	public Usuario findUsuarioByUsuario(String username);
	Usuario getUsuarioById(long id);
	public void save(Usuario usuario);
	public void crearUser(Usuario usuario);

	
}