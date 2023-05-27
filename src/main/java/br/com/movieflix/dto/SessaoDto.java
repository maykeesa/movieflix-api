package br.com.movieflix.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filial;
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
	private Sala sala;
	private Filme filme;
	private Filial filial;
	
	public SessaoDto(Sessao sessao) {
		this.id = sessao.getId();
		this.horarioSessao = sessao.getHorarioSessao();
		this.sala = sessao.getSalaId();
		this.filme = sessao.getFilmeId();
		this.filial = sessao.getFiliaId();
	}
	
	public static Page<SessaoDto> converter(Page<Sessao> sessoes) {
		return sessoes.map(SessaoDto::new);
	}
}
