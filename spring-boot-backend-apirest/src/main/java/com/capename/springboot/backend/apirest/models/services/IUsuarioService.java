package com.capename.springboot.backend.apirest.models.services;

import com.capename.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {
	
	public Usuario findByUsename( String username);

}
