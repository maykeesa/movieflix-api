package br.com.movieflix.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.CompraDto;
import br.com.movieflix.form.att.CompraAttForm;
import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.repository.CompraRepository;
import br.com.movieflix.repository.UsuarioRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository compraRep;

	@Autowired
	private UsuarioRepository userRep;
	
	// Retorna um booleano se compra existe ou nao
	public boolean isCompraPresent(UUID id) {
		Optional<Compra> comprarOpt = this.compraRep.findById(id);
		if (comprarOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna compra por Id
	public Compra getCompraById(UUID id) {
		Optional<Compra> compraOpt = this.compraRep.findById(id);
		if (compraOpt.isPresent()) {
			return compraOpt.get();
		}
		return null;
	}

	// paginacao de compras
	public Page<CompraDto> pageCompra(Pageable paginacao) {
		Page<Compra> compras = this.compraRep.findAll(paginacao);
		return CompraDto.converter(compras);
	}

	// Cadastra compra
	public URI cadastrar(Compra compra, UriComponentsBuilder uriBuilder) {
		this.addPontosUsuario(compra.getUsuario(), compra.getProdutosCompra());
		this.compraRep.save(compra);
		return uriBuilder.path("/compra/{id}").buildAndExpand(compra.getId()).toUri();
	}
	
	// Atualizar pontos para usuario
	public void addPontosUsuario(Usuario user, List<Produto> produtos) {
		int pontos = 0;
		if(produtos.size() != 0) {
			for(Produto produto : produtos) {
				int calcPontos = (int) (produto.getPreco().doubleValue() * 0.5);
				pontos += calcPontos;
			}
			
			user.addPontos(pontos);
			userRep.save(user);
		}
	}

	// Atualiza compra
	public Compra atualizar(UUID id, CompraAttForm compraAttForm) {
		Compra compra = compraAttForm.atualizar(this.getCompraById(id));
		this.compraRep.save(compra);
		return compra;
	}

	// Deleta compra
	public void deletarCompraById(UUID id) {
		this.compraRep.deleteById(id);
	}
}
