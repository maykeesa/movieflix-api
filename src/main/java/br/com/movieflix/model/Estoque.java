package br.com.movieflix.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Estoque {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private Long quantidade;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Produto produtoId;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;
	
	public Estoque(Long quantidade, Produto produto, Filial filial) {
		this.quantidade = quantidade;
		this.produtoId = produto;
		this.filialId = filial;
	}
}
