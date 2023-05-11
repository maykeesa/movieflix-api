package br.com.movieflix.controller;

import br.com.movieflix.model.Filme;
import br.com.movieflix.model.dto.FilmeDto;
import br.com.movieflix.model.form.FilmeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import br.com.movieflix.service.FilmeService;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    // Lista todos os filmes
    @GetMapping
    public Page<FilmeDto> listarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return this.filmeService.pageFilme(paginacao);
    }

    // Lista filme por Id
    @GetMapping("/{id}")
    public ResponseEntity<FilmeDto> listarUnico(@PathVariable UUID id) {
        if (this.filmeService.isFilmePresent(id)) {
            Filme filme = this.filmeService.getFilmeById(id);
            return ResponseEntity.ok(new FilmeDto(filme));
        }

        return ResponseEntity.notFound().build();
    }

    //cadastrar filme
    @PostMapping
    public ResponseEntity<FilmeDto> cadastrar(@RequestBody @Valid FilmeForm filmeForm, UriComponentsBuilder uriBuilder) {
        Filme filme = filmeForm.converter();
        URI uri = this.filmeService.cadastrar(filme, uriBuilder);
        return ResponseEntity.created(uri).body(new FilmeDto(filme));
    }

    //editar filme
    @PutMapping("/{id}")
    public ResponseEntity<FilmeDto> editar(@PathVariable UUID id, @RequestBody @Valid FilmeForm filmeForm) {
        if (this.filmeService.isFilmePresent(id)) {
            Filme filme = this.filmeService.atualizar(id, filmeForm);
            return ResponseEntity.ok(new FilmeDto(filme));
        }

        return ResponseEntity.notFound().build();
    }

    //deletar filme
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        if (this.filmeService.isFilmePresent(id)) {
            this.filmeService.deletar(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }



}
