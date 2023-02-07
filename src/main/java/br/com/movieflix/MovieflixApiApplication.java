package br.com.movieflix;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MovieflixApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(MovieflixApiApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
	}

}
