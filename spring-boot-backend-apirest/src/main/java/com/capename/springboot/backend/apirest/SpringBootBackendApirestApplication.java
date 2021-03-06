package com.capename.springboot.backend.apirest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringBootBackendApirestApplication extends SpringBootServletInitializer implements CommandLineRunner{
	
	@Autowired
	private BCryptPasswordEncoder passwordEndcoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendApirestApplication.class, args);
	}
	
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		
		return builder.sources( SpringBootBackendApirestApplication.class );
	}



	@Override
	public void run(String... args) throws Exception {
		String password = "12345";
		
		for ( int i= 0; i < 4; i++) {
			String passwordBcrypt = passwordEndcoder.encode(password);
			System.out.println(passwordBcrypt);
		}

		
	}

}
