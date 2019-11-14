package com.academia.main.servicios;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import com.academia.main.entidades.*;
import com.academia.main.repositorios.*;


@Service
@Validated
public class UsuarioServicioImpl implements UsuarioServicio {
	@Autowired private UsuarioRepository usuarioRepositorio;
	
	@Autowired private RolRepository rolRespositorio;
	
	@Autowired private BCryptPasswordEncoder bCryptPasswordEncoder;

	private Logger LOG = Logger.getLogger(UsuarioServicioImpl.class);
	
	@Override
	public Usuario findUsuarioByUsuario(String username) {
		return usuarioRepositorio.findByUsuario(username);
	}

	@Override
	public Usuario getUsuarioById(long id) {
		return usuarioRepositorio.getOne(id);
	}

	@Override
	@ResponseStatus(HttpStatus.CREATED)
	public void save(Usuario user) throws Exception {
		Usuario newUser = usuarioRepositorio.findByUsuario(user.getUsuario());
		if(newUser == null){
		user.setClave(bCryptPasswordEncoder.encode(user.getClave()));
		user.setHabilitado(true);
		Rol userRol = rolRespositorio.findByRole("ADMIN");
		LOG.info(userRol);
		Set<Rol> roles = new HashSet<Rol>(Arrays.asList(userRol)); 
		LOG.info(roles);
		user.setRoles(roles);
		usuarioRepositorio.save(user);
		}else{
			//usuarioRepositorio.save(newUser);
			throw new Exception("Usuario existente");
		}
	}

	@Override
	public void delete(Usuario usuario) {
		usuarioRepositorio.delete(usuario);
	}



}