package br.com.movieflix.model;

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
public class Sala {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	private int capacidade;
	private boolean ocupada = false;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;
	
}
