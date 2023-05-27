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
public class Catalogo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filme filmeId;
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filialId;
	
	public Catalogo(Filme filme, Filial filial) {
		this.filmeId = filme;
		this.filialId = filial;
	}
}
