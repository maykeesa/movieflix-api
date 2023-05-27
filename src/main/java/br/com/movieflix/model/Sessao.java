package br.com.movieflix.model;

import java.time.LocalDateTime;
import java.util.UUID;

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
public class Sessao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private LocalDateTime horarioSesao;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Sala salaId;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Filme filmeId;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Filial filiaId;
	
	public Sessao(LocalDateTime horarioSesao, Sala sala, Filme filme, Filial filial) {
		this.horarioSesao = horarioSesao;
		this.salaId = sala;
		this.filmeId = filme;
		this.filiaId = filial;
	}
}
