package com.capename.springboot.backend.apirest.models.dao.services;

import java.util.List;

import com.capename.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
}
