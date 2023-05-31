package br.com.movieflix.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.*;
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
	private BigDecimal preco;
	@Column(columnDefinition = "TEXT")
	private String srcSnack;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;
	
	public Produto(String nome, BigDecimal preco, Filial filialId, String srcSnack) {
		this.nome = nome;
		this.preco = preco;
		this.filialId = filialId;
		this.srcSnack = srcSnack;
	}
}
