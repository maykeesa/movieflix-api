package br.com.movieflix.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private BigDecimal preco;
	
	public Produto(String nome, String descricao, BigDecimal preco) {
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
	}
}
