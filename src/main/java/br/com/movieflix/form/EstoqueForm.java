package br.com.movieflix.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Estoque;
import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Produto;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.ProdutoRepository;
import lombok.Getter;

@Getter
public class EstoqueForm {

	@NotNull
	private Long quantidade;
	@NotNull
	private UUID produtoId;
	@NotNull
	private UUID filialId;
	
	public Estoque converterToModel(ProdutoRepository produtoRep, FilialRepository filialRep) {
		Produto produto = produtoRep.findById(this.produtoId).get();
		Filial filial = filialRep.findById(this.filialId).get();
		return new Estoque(this.quantidade, produto, filial);
	}
	
}
