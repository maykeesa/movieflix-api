package br.com.movieflix.form.att;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Produto;
import lombok.Setter;

@Setter
public class ProdutoAttForm {

	@NotBlank @NotEmpty
	private String nome;
	@NotNull
	private BigDecimal preco;
	@NotBlank @NotEmpty
	private String srcSnack;
	
	public Produto atualizar(Produto produto) {
		produto.setNome(this.nome);
		produto.setPreco(this.preco);
		produto.setSrcSnack(this.srcSnack);
		return produto;
	}
}
