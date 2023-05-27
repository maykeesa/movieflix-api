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

import br.com.movieflix.dto.CatalogoDto;
import br.com.movieflix.model.Catalogo;
import br.com.movieflix.model.Filial;
import br.com.movieflix.repository.CatalogoRepository;

@Service
public class CatalogoService {

	@Autowired
	private CatalogoRepository catalogoRep;

	// Retorna um booleano se catalogo existe ou nao
	public boolean isCatalogoPresent(UUID id) {
		Optional<Catalogo> catalogoOpt = this.catalogoRep.findById(id);
		if (catalogoOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna catalogo por filial Id
	public List<Catalogo> getCatalogoByFilialId(Filial filialId) {
		return this.catalogoRep.findByFilialId(filialId);
	}

	// paginacao de catalogo
	public Page<CatalogoDto> pageCatalogo(Pageable paginacao) {
		Page<Catalogo> catalogo = this.catalogoRep.findAll(paginacao);
		return CatalogoDto.converter(catalogo);
	}

	// Cadastra no catalogo
	public URI cadastrar(Catalogo catalogo, UriComponentsBuilder uriBuilder) {
		this.catalogoRep.save(catalogo);
		return uriBuilder.path("/catalogo/{id}").buildAndExpand(catalogo.getId()).toUri();
	}

	// Deleta catalogo
	public void deletarCatalogoById(UUID id) {
		this.catalogoRep.deleteById(id);
	}
}
