package br.com.movieflix.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario {

	@Id
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private LocalDateTime dataAniversario;
	private LocalDateTime contaCriada;
}
