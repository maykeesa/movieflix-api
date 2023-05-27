package br.com.movieflix.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.UniqueConstraint;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private LocalDateTime dataCompra;
	@OneToOne
	@JoinColumn(referencedColumnName = "cpf")
	private Usuario usuario;
	@ManyToMany
	@JoinTable(name = "produtosCompra", 
			   joinColumns = @JoinColumn(name = "compraId"), 
			   inverseJoinColumns = @JoinColumn(name = "produtoId"))
	private List<Produto> produtosCompra;
	
	public Compra(Usuario usuario, List<Produto> produtosCompra) {
		this.dataCompra = LocalDateTime.now();
		this.usuario = usuario;
		this.produtosCompra = produtosCompra;
	}
} 
