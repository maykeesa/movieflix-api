package br.com.movieflix.form.att;

import java.util.List;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import lombok.Setter;

@Setter
public class CompraAttForm {

	private List<Produto> produtosCompra;
	
	public Compra atualizar(Compra compra) {
		compra.setProdutosCompra(this.produtosCompra);
		return compra;
	}
}
