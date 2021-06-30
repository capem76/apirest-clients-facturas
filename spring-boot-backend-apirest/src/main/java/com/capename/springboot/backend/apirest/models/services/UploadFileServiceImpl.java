package com.capename.springboot.backend.apirest.models.services;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.capename.springboot.backend.apirest.util.Utileria;


@Service
public class UploadFileServiceImpl implements IUploadFileService {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);	
	private final static String DIRECTORIO_UPLOAD = "/home/ubuntu/Downloads/uploads";

	@Override
	public Resource cargarFoto(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso = null;
		logger.info("ruta fichero: " + rutaArchivo.toString());
		
		recurso = new UrlResource(rutaArchivo.toUri());
				
		if( !recurso.exists() && !recurso.isReadable() ) {
			rutaArchivo = Paths.get("src/main/resources/static/images").resolve("no-user-3.png").toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
			logger.error("error al cargar la foto" + nombreFoto);
		}
		
		return recurso;
	}

	@Override
	public String copiarArchivo(MultipartFile archivo, String clienteId) throws IOException {
		String nombreArchivoOriginal = archivo.getOriginalFilename();			
//		String nombreArchivo = UUID.randomUUID().toString().concat("-").concat( nombreArchivoOriginal.replace(" ", "-") );
		String nombreArchivo = Utileria.randomAlphaNumeric(10) + "-"+ "CLT" + clienteId +"-" + nombreArchivoOriginal.replace(" ", "-");
		Path rutaArchivo = getPath(nombreArchivo);
		logger.info("clase: " + this.getClass() + "method: " + "copiarArchivo " +  rutaArchivo.toString() );
		logger.info("archivo: " + archivo );
				
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		
		return nombreArchivo;
	}

	@Override
	public boolean eliminarFoto(String nombreFoto) {
		if( nombreFoto != null && nombreFoto.length() > 0 ) {		
			Path rutaFotoAnterior = getPath(nombreFoto);
			File archivoFotoAnterior = rutaFotoAnterior.toFile(); 
			if ( archivoFotoAnterior.exists() && archivoFotoAnterior.canRead() ) {
				archivoFotoAnterior.delete();
				return true;
			}				
		}

		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
