package com.capename.springboot.backend.apirest.util;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.capename.springboot.backend.apirest.controllers.ClienteRestController;

public class Utileria {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteRestController.class);
	
	public static String guardarArchivo( MultipartFile multiPart, String ruta ) {
		String nombreOriginal = multiPart.getOriginalFilename();
		nombreOriginal = nombreOriginal.replace(" ", "-");
		
		try {
			File imageFile = new File( ruta + nombreOriginal );
			logger.debug("Archivo: " + imageFile.getAbsolutePath());
			multiPart.transferTo(imageFile);
			return nombreOriginal;
		}catch (IOException e) {		
			e.printStackTrace();
			logger.error("Error: " + e.getCause().getMessage() );
			return null;
			
		}
		
	}
	
	/**
	 * 
	 * @param count
	 * @return
	 */
	public static String randomAlphaNumeric( int count ) {
		String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		StringBuilder builder = new StringBuilder();
		while( count-- != 0 ) {
			int character = (int) (Math.random() * CARACTERES.length());
			builder.append(CARACTERES.charAt(character));			
		}
		
		return builder.toString();
	}

}
