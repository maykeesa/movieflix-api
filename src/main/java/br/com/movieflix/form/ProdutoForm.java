package br.com.movieflix.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Produto;
import lombok.Getter;

@Getter
public class ProdutoForm {

	@NotBlank @NotEmpty
	private String nome;
	@NotBlank @NotEmpty
	private String descricao;
	@NotNull
	private BigDecimal preco;
	@NotBlank @NotEmpty
	private String srcSnack;
	
	public Produto converterToModel() {
		return new Produto(this.nome, this.descricao, this.preco, this.srcSnack);
	}	
}
