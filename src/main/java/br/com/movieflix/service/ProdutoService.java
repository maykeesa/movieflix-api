package br.com.movieflix.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.ProdutoDto;
import br.com.movieflix.form.att.ProdutoAttForm;
import br.com.movieflix.model.Produto;
import br.com.movieflix.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRep;
	
	// Retorna um booleano se produto existe ou nao
	public boolean isProdutoPresent(UUID id) {
		Optional<Produto> produtoOpt = this.produtoRep.findById(id);
		if(produtoOpt.isPresent()) {
			return true;
		}
		return false;
	}
    
    // Retorna produto por Id
    public Produto getProdutoById(UUID id) {
        Optional<Produto> produtoOpt = this.produtoRep.findById(id);
        if(produtoOpt.isPresent()){
            return produtoOpt.get();
        }
        return null;
    }
    
    // Transforma uma lista de UUID de produtos em uma lista de Produtos
    public ArrayList<Produto> uuidToProduto(List<UUID> produtosId, ProdutoRepository produtoRep){
    	ArrayList<Produto> produtos = new ArrayList<Produto>();
    	if(produtosId.size() != 0) {
    		produtosId.forEach(i -> produtos.add(produtoRep.findById(i).get()));
    		return produtos;
    	}
    	
    	return null;
    }

    // Paginacao de produtos
    public Page<ProdutoDto> pageProduto(Pageable paginacao) {
        Page<Produto> produtos = this.produtoRep.findAll(paginacao);
        return ProdutoDto.converter(produtos);
    }
    
	// Cadastra produto
	public URI cadastrar(Produto produto, UriComponentsBuilder uriBuilder) {
		this.produtoRep.save(produto);
		return uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
	}
	
	// Atualiza produto
	public Produto atualizar(UUID id, ProdutoAttForm produtoAttForm) {
		Produto produto = produtoAttForm.atualizar(this.getProdutoById(id));
		this.produtoRep.save(produto);
		return produto;
	}
	
	// Deletar produto
	public void deletarProdutoById(UUID id) {
		this.produtoRep.deleteById(id);
	}
}
