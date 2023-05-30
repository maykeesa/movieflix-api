package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.EstoqueDto;
import br.com.movieflix.model.Estoque;
import br.com.movieflix.repository.EstoqueRepository;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository estoqueRep;

	// Retorna um booleano se estoque existe ou nao
	public boolean isEstoquePresent(UUID id) {
		Optional<Estoque> estoqueOpt = this.estoqueRep.findById(id);
		if(estoqueOpt.isPresent()) {
			return true;
		}
		return false;
	}
    
    // Retorna estoque por filial
    public Estoque getEstoqueById(UUID id) {
        Optional<Estoque> estoqueOpt = this.estoqueRep.findById(id);
        if(estoqueOpt.isPresent()){
            return estoqueOpt.get();
        }
        return null;
    }

    // Paginacao de estoque
    public Page<EstoqueDto> pageEstoque(Pageable paginacao) {
        Page<Estoque> estoque = this.estoqueRep.findAll(paginacao);
        return EstoqueDto.converter(estoque);
    }
    
	// Cadastra estoque
	public URI cadastrar(Estoque estoque, UriComponentsBuilder uriBuilder) {
		this.estoqueRep.save(estoque);
		return uriBuilder.path("/estoque/{id}").buildAndExpand(estoque.getId()).toUri();
	}
	
	// Deletar estoque
	public void deletarEstoqueById(UUID id) {
		this.estoqueRep.deleteById(id);
	}
}
