package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.dto.FilialDto;
import br.com.movieflix.model.form.FilialForm;
import br.com.movieflix.repository.FilialRepository;

@Service
public class FilialService {

    @Autowired
    private FilialRepository filialRep;

	// Retorna um booleano se filial existe ou nao
	public boolean isFilialPresent(UUID id) {
		Optional<Filial> filialOpt = this.filialRep.findById(id);
		if(filialOpt.isPresent()) {
			return true;
		}
		return false;
	}
    
    // Retorna filial por Id
    public Filial getFilialById(UUID id) {
        Optional<Filial> filialOpt = this.filialRep.findById(id);
        if(filialOpt.isPresent()){
            return filialOpt.get();
        }
        return null;
    }

    // Paginacao de filiais
    public Page<FilialDto> pageFilial(Pageable paginacao) {
        Page<Filial> filiais = this.filialRep.findAll(paginacao);
        return FilialDto.converter(filiais);
    }
    
	// Cadastra filial
	public URI cadastrar(Filial func, UriComponentsBuilder uriBuilder) {
		this.filialRep.save(func);
		return uriBuilder.path("/filial/{id}").buildAndExpand(func.getId()).toUri();
	}
	
	// Atualiza filial
	public Filial atualizar(UUID id, FilialForm filialForm) {
		Filial filial = filialForm.atualizar(this.getFilialById(id));
		this.filialRep.save(filial);
		return filial;
	}
	
	// Deletar filial
	public void deletarFilialById(UUID id) {
		this.filialRep.deleteById(id);
	}
}
