package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SalaDto {

	private UUID id;
	private String nome;
	private int capacidade;
	private boolean ocupada;
	private Filial filial;
	
	public SalaDto(Sala sala) {
		this.id = sala.getId();
		this.nome = sala.getNome();
		this.capacidade = sala.getCapacidade();
		this.ocupada = sala.isOcupada();
		this.filial = sala.getFilialId();
	}
	
	public static Page<SalaDto> converter(Page<Sala> salas) {
		return salas.map(SalaDto::new);
	}
}
