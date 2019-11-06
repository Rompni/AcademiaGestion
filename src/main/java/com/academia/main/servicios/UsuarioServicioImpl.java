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
	@Autowired private UsuarioRepository usuarioRepositorio;
	
	@Autowired private RolRepository rolRespositorio;
	
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Usuario findUsuarioByUsuario(String username) {
		return usuarioRepositorio.findByUsuario(username);
	}

	@Override
	public Usuario getUsuarioById(long id) {
		return usuarioRepositorio.getOne(id);
	}

	@Override
	public void save(Usuario user) {
		Usuario newUser = usuarioRepositorio.findByUsuario(user.getUsuario());
		if(newUser == null){
		user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
		user.setHabilitado(true);
		Rol userRol = rolRespositorio.findByRole("ADMIN");
		user.setRoles(new HashSet<Rol>(Arrays.asList(userRol)));
		usuarioRepositorio.save(user);
		}
		usuarioRepositorio.save(newUser);
	}
/*
	@Override
	public void crearUsuario(String user, String rol, String clave) {
		Usuario usuario = new Usuario();
		usuario.setUsuario(user);
		usuario.setClave(bCryptPasswordEncoder.encode(clave));
		usuario.setHabilitado(true);
		Rol userRol = rolRespositorio.findByRole(rol);
		usuario.setRoles(new HashSet<Rol>(Arrays.asList(userRol)));
		usuarioRepositorio.save(usuario);

	} */

	public void crearUser (Usuario usr) {
		usuarioRepositorio.save(usr);

	}

}