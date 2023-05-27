package br.com.movieflix.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Catalogo;
import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Filme;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.FilmeRepository;
import lombok.Getter;

@Getter
public class CatalogoForm {

	@NotNull
	private UUID filmeId;
	@NotNull
	private UUID filialId;
	
	public Catalogo converterToModel(FilmeRepository filmeRep, FilialRepository filiaRep) {
		Filme filme = filmeRep.findById(this.filmeId).get();
		Filial filial = filiaRep.findById(this.filialId).get();
		return new Catalogo(filme, filial);
	}
}
