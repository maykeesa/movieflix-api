package br.com.movieflix.model;

import java.math.BigDecimal;
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
public class Funcionario{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	private boolean isGerent;
	private String email;
	private String senha;
	private BigDecimal salario;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;
	
	public Funcionario(String nome, String email, String senha, Filial filial) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.filialId = filial;
	}
	
}
