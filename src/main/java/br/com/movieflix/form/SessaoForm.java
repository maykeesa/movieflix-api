package br.com.movieflix.form;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Filme;
import br.com.movieflix.model.Sala;
import br.com.movieflix.model.Sessao;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.FilmeRepository;
import br.com.movieflix.repository.SalaRepository;
import lombok.Getter;

@Getter
public class SessaoForm {

	@NotNull
	private List<String> horariosSessao;
	@NotNull
	private UUID salaId;
	@NotNull
	private UUID filmeId;
	@NotNull
	private UUID filialId;
	
	public Sessao converterToModel(SalaRepository salaRep, FilmeRepository filmeRep, FilialRepository filialRep) {
		Sala sala = salaRep.findById(this.salaId).get();
		Filme filme = filmeRep.findById(this.filmeId).get();
		Filial filial = filialRep.findById(this.filialId).get();
		return new Sessao(sala, filme, filial);
	}
}
