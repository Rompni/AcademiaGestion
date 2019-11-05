package com.academia.main.servicios;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.academia.main.entidades.*;
import com.academia.main.repositorios.*;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {
	@Autowired
	private UsuarioRepository usuarioRepositorio;
	@Autowired
	private RolRepository rolRespositorio;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Usuario findUsuarioByUsuario(String username) {
		return usuarioRepositorio.findByUsuario(username);
	}

	public Usuario getUsuarioById(long id) {
		return usuarioRepositorio.getOne(id);
	}
	public void save(Usuario usuario) {
		usuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));
		usuario.setHabilitado(true);
		Rol userRol = rolRespositorio.findByRole("ADMIN");
		usuario.setRoles(new HashSet<Rol>(Arrays.asList(userRol)));
		usuarioRepositorio.save(usuario);
		
	}

}