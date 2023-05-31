package br.com.movieflix.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Produto;
import lombok.Getter;

@Getter
public class ProdutoForm {

	@NotBlank @NotNull @NotEmpty
	private String nome;
	@NotNull
	private BigDecimal preco;
	
	public Produto converterToModel() {
		return new Produto(this.nome, this.preco);
	}	
}
