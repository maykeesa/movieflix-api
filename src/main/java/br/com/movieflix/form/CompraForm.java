package br.com.movieflix.form;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.repository.ProdutoRepository;
import br.com.movieflix.repository.UsuarioRepository;
import br.com.movieflix.service.DateService;
import br.com.movieflix.service.ProdutoService;
import lombok.Getter;

@Getter
public class CompraForm {

	private String dataCompra;
	private String usuarioId;
	private ArrayList<UUID> produtosId;
	
	public Compra converterToModel(UsuarioRepository userRep, ProdutoRepository produtoRep, ProdutoService produtoService) {
		LocalDateTime dataCompra = DateService.dataStringToClass(this.dataCompra);
		Usuario usuario = userRep.findById(this.usuarioId).get();
		ArrayList<Produto> produtos = produtoService.uuidToProduto(produtosId, produtoRep);
		return new Compra(dataCompra, usuario, produtos);
	}
}
