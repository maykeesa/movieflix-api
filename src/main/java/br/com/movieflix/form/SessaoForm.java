package br.com.movieflix.form;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Filme;
import br.com.movieflix.model.Sala;
import br.com.movieflix.model.Sessao;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.FilmeRepository;
import br.com.movieflix.repository.SalaRepository;
import br.com.movieflix.service.DateService;
import lombok.Getter;

@Getter
public class SessaoForm {

	@NotBlank @NotEmpty
	private String horarioSessao;
	@NotNull
	private UUID salaId;
	@NotNull
	private UUID filmeId;
	@NotNull
	private UUID filialId;
	
	public Sessao converterToModel(SalaRepository salaRep, FilmeRepository filmeRep, FilialRepository filialRep) {
		LocalDateTime dataSessao = DateService.dataSessaoToClass(horarioSessao);
		Sala sala = salaRep.findById(this.salaId).get();
		Filme filme = filmeRep.findById(this.filmeId).get();
		Filial filial = filialRep.findById(this.filialId).get();
		return new Sessao(dataSessao, sala, filme, filial);
	}
}
