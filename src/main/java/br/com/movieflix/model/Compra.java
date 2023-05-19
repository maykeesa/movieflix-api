package br.com.movieflix.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "cpf")
	private Usuario usuario;
	private ArrayList<Produto> produtos;
} 
