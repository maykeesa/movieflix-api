package br.com.movieflix.form;

import java.util.List;
import java.util.UUID;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.repository.ProdutoRepository;
import br.com.movieflix.repository.UsuarioRepository;
import br.com.movieflix.service.ProdutoService;
import lombok.Getter;

@Getter
public class CompraForm {

	private String usuarioId;
	private List<UUID> produtosId;
	
	public Compra converterToModel(UsuarioRepository userRep, ProdutoRepository produtoRep, ProdutoService produtoService) {
		Usuario usuario = userRep.findById(this.usuarioId).get();
		List<Produto> produtos = produtoService.uuidToProduto(produtosId, produtoRep);
		return new Compra(usuario, produtos);
	}
}
