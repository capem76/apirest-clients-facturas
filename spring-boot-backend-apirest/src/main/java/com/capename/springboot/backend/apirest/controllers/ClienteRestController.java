package com.capename.springboot.backend.apirest.controllers;



import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.capename.springboot.backend.apirest.models.entity.Cliente;
import com.capename.springboot.backend.apirest.models.entity.Region;
import com.capename.springboot.backend.apirest.models.services.IClienteService;
import com.capename.springboot.backend.apirest.models.services.IUploadFileService;

@CrossOrigin( origins = {"http://localhost:4200"} )
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	@GetMapping("/clientes")
	public List<Cliente> index(){		
		return clienteService.findAll();
		
	}
	
	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index( @PathVariable Integer page ){	
		Pageable pageable = PageRequest.of(page, 4);
		
		return clienteService.findAll( pageable )  ;
		
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
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
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")	
	public ResponseEntity<?> create( @Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteNuevo = null; 
		Map<String, Object> response = new HashMap<>();
		
		if ( result.hasErrors() ) {
			/*
			 * Antes de Java 8
			List<String> errors = new ArrayList<>();
			
			for ( FieldError fieldErr: result.getFieldErrors() ) {
				errors.add("El campo '".concat( fieldErr.getField() ).concat("' ").concat( fieldErr.getDefaultMessage() ));
			} 
			*/
			
//			con java 8
			List<String> errors = result.getFieldErrors()
					.stream()
					.map( fieldErr -> "El campo '".concat( fieldErr.getField() ).concat("' ").concat( fieldErr.getDefaultMessage() )  )
					.collect( Collectors.toList() );
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
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
	
	@Secured( "ROLE_ADMIN" )
	@PutMapping("/clientes/{id}")	
	public ResponseEntity<?> update( @Valid @RequestBody Cliente cliente, BindingResult result ,@PathVariable Long id ) {
		
		Cliente clienteActualizado = null;		
		Map<String, Object>response = new HashMap<>();
		
		Cliente clienteActual = clienteService.findById(id);
		
		if ( result.hasErrors() ) {
			
			 List<String> errors = result.getFieldErrors()
				 .stream()			  
				 .map( fieldError -> "El campo '".concat( fieldError.getField() ).concat("' ").concat( fieldError.getDefaultMessage() ) )
				 .collect( Collectors.toList() );
			 
			 response.put("errors", errors);
			 return new ResponseEntity<Map<String, Object>>( response, HttpStatus.BAD_REQUEST );
			
		}
				
		if (clienteActual == null) {
			response.put( "mensaje", "Error: no se pudo editar el cliente ID: ".concat(id.toString().concat(" no existe ne la BDD ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());	
			clienteActual.setCreateAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			
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
	
	@Secured( "ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")	
	public ResponseEntity<?> delete( @PathVariable Long id ) {
		Map<String, Object> response = new HashMap<>();		
		
		try {
			Cliente cliente = clienteService.findById(id);			
			if (cliente == null) {
				response.put("mensaje", "Cliente id: " + id + " no existe!");				
				return new ResponseEntity<Map<String, Object>>( response, HttpStatus.NOT_FOUND );				
			}
			String nombreFotoAnterior = cliente.getFoto();	
			
			@SuppressWarnings("unused")
			boolean isFotoDeleted = uploadService.eliminarFoto(nombreFotoAnterior);			
			
			clienteService.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al borrar cliente en la BDD");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		response.put("mensaje", "Cliente "+ id +"ha  sido eliminado con exito");
		
		
		return new ResponseEntity<Map<String, Object>>( response, HttpStatus.OK );
	}
	
	@Secured({ "ROLE_ADMIN", "ROLE_USER" })
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload( @RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id ){		
		Map<String, Object> response = new HashMap<>();
		
		Cliente cliente = clienteService.findById(id);	
		
		if (cliente == null) {
			response.put("error", "Cliente id: " + id + " no existe!");				
			return new ResponseEntity<Map<String, Object>>( response, HttpStatus.NOT_FOUND );				
		}		
		
		if (!archivo.isEmpty()) {	
			String nombreArchivo = "";
			try {
				nombreArchivo = uploadService.copiarArchivo(archivo, cliente.getId().toString() );
			} catch (IOException e) {
				logger.error("Error en subir fichero: " + e.getCause().getMessage());
				e.printStackTrace();
				response.put("mensaje", "Error al subir fichero ");
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
			}
			
			String nombreFotoAnterior = cliente.getFoto();
			
			uploadService.eliminarFoto(nombreFotoAnterior);
			
			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente la imagen " + nombreArchivo);
			
		}
		
		
		
		return new ResponseEntity<Map<String,Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/clientes/uploads/img/{nombreFoto:.+}")
	public ResponseEntity<?> verFoto( @PathVariable String nombreFoto ) {
		Map<String, Object> response = new HashMap<>();
		Resource recurso = null;
		
		try {
			recurso = uploadService.cargarFoto(nombreFoto);
		} catch (MalformedURLException e) {			
			e.printStackTrace();
			response.put("mensaje", "al cargar foto ");
			response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>( response, HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
		HttpHeaders cabeceraHttp = new HttpHeaders();
		cabeceraHttp.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabeceraHttp, HttpStatus.OK);
		
	}
	
	@Secured( "ROLE_ADMIN" )
	@GetMapping("/clientes/regiones")
	public List<Region> listarRegiones(){
		return clienteService.findAllRegiones();
	}
	
	
	
		
}
	


