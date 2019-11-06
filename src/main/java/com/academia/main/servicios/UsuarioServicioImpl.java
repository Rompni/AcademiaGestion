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
		usuario.setUsuario(usuario.getUsuario());
		usuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));
		usuario.setHabilitado(true);
		Rol userRol = rolRespositorio.findByRole(usuario.getRole());
		usuario.setRoles(new HashSet<Rol>(Arrays.asList(userRol)));
		usuarioRepositorio.save(usuario);
		
	}

	@Override
	public void crearUsuario(String user, String Rol, String clave) {
		Usuario usuario = new Usuario();
		usuario.setClave(bCryptPasswordEncoder.encode(clave));
		usuario.setHabilitado(true);
		Rol userRol = rolRespositorio.findByRole(Rol);
		usuario.setRoles(new HashSet<Rol>(Arrays.asList(userRol)));
		usuarioRepositorio.save(usuario);
	}

}