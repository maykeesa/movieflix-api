package br.com.movieflix.form;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Sala;
import br.com.movieflix.repository.FilialRepository;
import lombok.Getter;

@Getter
public class SalaForm {

	@NotBlank @NotEmpty
	private String nome;
	@NotNull
	private int capacidade;
	@NotNull
	private UUID filialId;
	
	public Sala converterToModel(FilialRepository filialRep) {
		Filial filial = filialRep.findById(this.filialId).get();
		return new Sala(nome, capacidade, filial);
	}
}
