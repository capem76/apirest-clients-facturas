package com.capename.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.capename.springboot.backend.apirest.models.entity.Cliente;
import com.capename.springboot.backend.apirest.models.entity.Region;

public interface IClienteService {
	
	public Page<Cliente> findAll( Pageable pageable );
	
	public List<Cliente> findAll();
	
	public Cliente save( Cliente cliente );
	
	public void delete( Long id );
	
	public Cliente findById(Long id);
	
	public List<Region> findAllRegiones(); 
}