package com.capename.springboot.backend.apirest.models.dao.services;

import java.util.List;

import com.capename.springboot.backend.apirest.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	
	public Cliente save( Cliente cliente );
	
	public void delete( Long id );
	
	public Cliente findById(Long id);
}
