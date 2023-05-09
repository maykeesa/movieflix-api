package br.com.movieflix.model;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Produto {

	private UUID id;
	private String nome;
	private BigDecimal preco;
	private int quantidade;
	@OneToMany
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;
}
