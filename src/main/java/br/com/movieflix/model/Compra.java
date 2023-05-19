package br.com.movieflix.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

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
	@JoinTable(name = "compraId", joinColumns = @JoinColumn(name = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id"))
	private ArrayList<Produto> produtos;
	
	public Compra(LocalDateTime dataCompra, Usuario usuario, ArrayList<Produto> produtos) {
		this.dataCompra = dataCompra;
		this.usuario = usuario;
		this.produtos = produtos;
	}
} 
