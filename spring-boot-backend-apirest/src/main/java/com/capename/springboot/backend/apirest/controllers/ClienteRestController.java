package com.capename.springboot.backend.apirest.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.exception.DataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capename.springboot.backend.apirest.models.dao.services.IClienteService;
import com.capename.springboot.backend.apirest.models.entity.Cliente;

@CrossOrigin( origins = {"http://localhost:4200"} )
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){		
		return clienteService.findAll();
		
	}
	
	@GetMapping("/clientes/{id}")	
	public ResponseEntity<?> show( @PathVariable Long id ) {
		
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		
		try{			
			cliente = clienteService.findById(id);
			
		} catch(DataAccessException e) {
			response.put( "mensaje", "Error al realizar la consulta en la BDD");
			response.put( "error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage() ));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(cliente == null) {
			response.put( "mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos!") ));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		response.put("cliente", cliente);
		
		return new ResponseEntity<Map<String,Object>>( response, HttpStatus.OK );
		
	}
	
	@PostMapping("/clientes")	
	public ResponseEntity<?> create( @RequestBody Cliente cliente) {
		Cliente clienteNuevo = null; 
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteNuevo = clienteService.save(cliente);
		} catch (DataAccessException e) {
			logger.error("Error al crear el cliente ".concat( e.getMostSpecificCause().getMessage() ), e.fillInStackTrace() );			
			response.put("mensaje", "Error al crear cliente en la BDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El Cliente se ha creado con exito!!");
		response.put("cliente", clienteNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED );
	}
	
	@PutMapping("/clientes/{id}")	
	public ResponseEntity<?> update( @RequestBody Cliente cliente, @PathVariable Long id ) {
		
		Cliente clienteActualizado = null;		
		Map<String, Object>response = new HashMap<>();
		
		Cliente clienteActual = clienteService.findById(id);
				
		if (clienteActual == null) {
			response.put( "mensaje", "Error: no se pudo editar el cliente ID: ".concat(id.toString().concat(" no existe ne la BDD ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());			
			
			clienteActualizado = clienteService.save(clienteActual);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar update en la BDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		
		response.put("mensaje", "Cliente ha sido actualizado con extio");
		response.put("cliente", clienteActualizado);
		
		return new ResponseEntity<Map<String, Object>>( response, HttpStatus.OK ) ;
	}
	
	@DeleteMapping("/clientes/{id}")	
	public ResponseEntity<?> delete( @PathVariable Long id ) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			clienteService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar cliente en la BDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		response.put("mensaje", "Cliente ha  sido eliminado con exito");
		
		
		return new ResponseEntity<Map<String, Object>>( response, HttpStatus.OK );
	}
	
	
		
}
	


