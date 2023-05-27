package br.com.movieflix.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filme;
import br.com.movieflix.model.Sala;
import br.com.movieflix.model.Sessao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SessaoDto {

	private UUID id;
	private LocalDateTime horarioSessao;
	private Filme filme;
	private Sala sala;
	
	public SessaoDto(Sessao sessao) {
		this.id = sessao.getId();
		this.horarioSessao = sessao.getHorarioSessao();
		this.filme = sessao.getFilmeId();
		this.sala = sessao.getSalaId();
	}
	
	public static Page<SessaoDto> converter(Page<Sessao> sessoes) {
		return sessoes.map(SessaoDto::new);
	}
}
