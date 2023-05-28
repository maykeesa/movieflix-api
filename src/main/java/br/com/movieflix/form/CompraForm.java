package br.com.movieflix.form;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.repository.ProdutoRepository;
import br.com.movieflix.repository.UsuarioRepository;
import br.com.movieflix.service.ProdutoService;
import lombok.Getter;

@Getter
public class CompraForm {
	
	@NotBlank @NotEmpty
	private String usuarioId;
	@NotNull
	private List<UUID> produtosId;
	
	public Compra converterToModel(UsuarioRepository userRep, ProdutoRepository produtoRep, ProdutoService produtoService) {
		Usuario usuario = userRep.findById(this.usuarioId).get();
		List<Produto> produtos = produtoService.uuidToProduto(produtosId, produtoRep);
		return new Compra(usuario, produtos);
	}
}
