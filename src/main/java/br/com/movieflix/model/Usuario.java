package br.com.movieflix.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Usuario implements UserPassword{

	@Id
	private String cpf;
	private String role;
	private String nome;
	private String email;
	private String senha;
	private LocalDateTime dataNascimento;
	private LocalDateTime contaCriada;
	
	public Usuario(String cpf, String nome, String email, String senha, LocalDateTime dataNascimento) {
		this.cpf = cpf;
		this.role = "user";
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.dataNascimento = dataNascimento;
		this.contaCriada = LocalDateTime.now();
	}
}
