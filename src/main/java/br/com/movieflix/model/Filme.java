package br.com.movieflix.model;

import java.util.UUID;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Filme {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String nome;
	private int nota;
	@Column(columnDefinition = "TEXT")
	private String sinopse;
	private String diretor;
	private String dataLancamento;
}
