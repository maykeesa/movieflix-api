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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.FilialDto;
import br.com.movieflix.form.FilialForm;
import br.com.movieflix.model.Filial;
import br.com.movieflix.service.FilialService;

@RestController
@RequestMapping("/filial")
public class FilialController {

	@Autowired
	private FilialService filialService;

	// Lista todas as filiais
	@GetMapping
	public Page<FilialDto> listarTodos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.filialService.pageFilial(paginacao);
	}

	// Lista filial por Id
	@GetMapping("/{id}")
	public ResponseEntity<FilialDto> listarUnico(@PathVariable UUID id) {
		if (this.filialService.isFilialPresent(id)) {
			Filial filial = this.filialService.getFilialById(id);
			return ResponseEntity.ok(new FilialDto(filial));
		}

		return ResponseEntity.notFound().build();
	}

	// Cadastrar filial
	@PostMapping
	public ResponseEntity<FilialDto> cadastrar(@RequestBody @Valid FilialForm filialForm, UriComponentsBuilder uriBuilder) {
		Filial filial = filialForm.converterToModel();
		URI uri = this.filialService.cadastrar(filial, uriBuilder);
		return ResponseEntity.created(uri).body(new FilialDto(filial));
	}

	// Editar filial
	@PutMapping("/{id}")
	public ResponseEntity<FilialDto> atualizar(@PathVariable UUID id, @RequestBody @Valid FilialForm filialForm) {
		if (this.filialService.isFilialPresent(id)) {
			Filial filial = this.filialService.atualizar(id, filialForm);
			return ResponseEntity.ok(new FilialDto(filial));
		}

		return ResponseEntity.notFound().build();
	} 

	// Deletar filial
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable UUID id) {
		if (this.filialService.isFilialPresent(id)) {
			this.filialService.deletarFilialById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
