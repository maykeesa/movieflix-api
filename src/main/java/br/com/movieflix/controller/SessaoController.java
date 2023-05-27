package br.com.movieflix.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.SessaoDto;
import br.com.movieflix.form.SessaoForm;
import br.com.movieflix.model.Sessao;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.FilmeRepository;
import br.com.movieflix.repository.SalaRepository;
import br.com.movieflix.service.SessaoService;

@RestController
@RequestMapping("/sessao")
public class SessaoController {
	
	@Autowired
	private SessaoService sessaoService;
	@Autowired
	private SalaRepository salaRep;
	@Autowired
	private FilmeRepository filmeRep;
	@Autowired
	private FilialRepository filialRep;

	// Lista todas as sessoes
	@GetMapping
	public Page<SessaoDto> listarTodos(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return sessaoService.pageSessao(paginacao);
	}

	// Lista sessao por Id
	@GetMapping("/{id}")
	public ResponseEntity<SessaoDto> listarUnico(@PathVariable UUID id) {
		if (this.sessaoService.isSessaoPresent(id)) {
			Sessao sessao = this.sessaoService.getSessaoById(id);
			return ResponseEntity.ok(new SessaoDto(sessao));
		}

		return ResponseEntity.notFound().build();
	}

	// Cadastrar sessao
	@PostMapping
	public ResponseEntity<SessaoDto> cadastrar(@RequestBody @Valid SessaoForm sessaoForm,
			UriComponentsBuilder uriBuilder) {
		Sessao sessao = sessaoForm.converterToModel(salaRep, filmeRep, filialRep);
		URI uri = this.sessaoService.cadastrar(sessao, uriBuilder);
		return ResponseEntity.created(uri).body(new SessaoDto(sessao));
	}

	// Deletar sessao
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable UUID id) {
		if (this.sessaoService.isSessaoPresent(id)) {
			this.sessaoService.deletarSessaoById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
