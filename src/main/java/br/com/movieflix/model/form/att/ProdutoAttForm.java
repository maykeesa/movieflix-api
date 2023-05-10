package br.com.movieflix.model.form.att;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Produto;

public class ProdutoAttForm {

	@NotBlank @NotNull @NotEmpty
	private String nome;
	@NotBlank @NotNull @NotEmpty
	private BigDecimal preco;
	
	public Produto atualizar(Produto produto) {
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		return produto;
	}
}
