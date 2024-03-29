package br.com.movieflix.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Administrador implements UserPassword{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String role;
	private String nome;
	private String email;
	private String senha;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;

	public Administrador(String nome, String email, String senha, Filial filialId) {
		this.role = "admin";
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.filialId = filialId;
	}
}
