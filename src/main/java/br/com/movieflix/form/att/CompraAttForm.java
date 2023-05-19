package br.com.movieflix.form.att;

import java.util.ArrayList;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import lombok.Setter;

@Setter
public class CompraAttForm {

	private ArrayList<Produto> produtos;
	
	public Compra atualizar(Compra compra) {
		compra.setProdutos(this.produtos);
		return compra;
	}
}
