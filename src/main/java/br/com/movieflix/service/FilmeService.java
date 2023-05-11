package br.com.movieflix.service;


import br.com.movieflix.model.Filme;
import br.com.movieflix.model.dto.FilmeDto;
import br.com.movieflix.model.form.FilmeForm;
import br.com.movieflix.repository.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRep;
    //Retorna um booleano se filme existe ou nao
    public boolean isFilmePresent(UUID id) {
        Optional<Filme> filmeOpt = this.filmeRep.findById(id);
        if(filmeOpt.isPresent()) {
            return true;
        }
        return false;
    }

    //Retorna filme por Id
    public Filme getFilmeById(UUID id) {
        Optional<Filme> filmeOpt = this.filmeRep.findById(id);
        if(filmeOpt.isPresent()){
            return filmeOpt.get();
        }
        return null;
    }

    //paginacao de filmes
    public Page<FilmeDto> pageFilme(Pageable paginacao) {
        Page<Filme> filmes = this.filmeRep.findAll(paginacao);
        return FilmeDto.converter(filmes);
    }

    //Cadastra filme
    public URI cadastrar(Filme func, UriComponentsBuilder uriBuilder) {
        this.filmeRep.save(func);
        return uriBuilder.path("/filme/{id}").buildAndExpand(func.getId()).toUri();
    }

    //Atualiza filme
    public Filme atualizar(UUID id, FilmeForm filmeForm) {
        Filme filme = filmeForm.atualizar(this.getFilmeById(id));
        this.filmeRep.save(filme);
        return filme;
    }

    //Deleta filme
    public void deletar(UUID id) { this.filmeRep.deleteById(id); }

}
