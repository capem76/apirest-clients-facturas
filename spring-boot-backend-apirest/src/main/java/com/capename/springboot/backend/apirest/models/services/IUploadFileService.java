package com.capename.springboot.backend.apirest.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {
	
	public Resource cargarFoto( String nombreFoto ) throws MalformedURLException;
	public String copiarArchivo( MultipartFile archivo, String clienteId ) throws IOException;
	public boolean eliminarFoto( String nombreFoto);
	public Path getPath( String nombreFoto );  

}
