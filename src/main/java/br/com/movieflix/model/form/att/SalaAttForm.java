package br.com.movieflix.model.form.att;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Sala;
import lombok.Setter;

@Setter
public class SalaAttForm {

	@NotBlank @NotEmpty
	private String nome;
	@NotNull
	private int capacidade;
	
	public Sala atualizar(Sala sala) {
		sala.setNome(this.nome);
		sala.setCapacidade(this.capacidade);
		return sala;
	}
}
