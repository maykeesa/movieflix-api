package br.com.movieflix.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
	@Column(columnDefinition = "TEXT")
	private String srcCapa;
	private LocalDateTime dataLancamento;

	public Filme(String nome, int nota, String sinopse, String diretor, LocalDateTime dataLancamento) {
		this.nome = nome;
		this.nota = nota;
		this.sinopse = sinopse;
		this.diretor = diretor;
		this.srcCapa = "https://shre.ink/capaUndefined";
		this.dataLancamento = dataLancamento;
	}
	
	public Filme(String nome, int nota, String sinopse, String diretor, String src,
			LocalDateTime dataLancamento) {
		this.nome = nome;
		this.nota = nota;
		this.sinopse = sinopse;
		this.diretor = diretor;
		this.srcCapa = src;
		this.dataLancamento = dataLancamento;
	}
}
