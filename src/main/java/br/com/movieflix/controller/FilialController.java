package br.com.movieflix.controller;

import java.net.URI;
import java.util.Optional;
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

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.dto.FilialDto;
import br.com.movieflix.model.form.FilialForm;
import br.com.movieflix.repository.FilialRepository;

@RestController
@RequestMapping("/filial")
public class FilialController {

	@Autowired
	private FilialRepository filialRep;

	// Lista todos os funcionarios
	@GetMapping
	public Page<FilialDto> listarTodos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Filial> filiais = this.filialRep.findAll(paginacao);
		return FilialDto.converter(filiais);
	}

	// Lista funcionario por Id
	@GetMapping("/{id}")
	public ResponseEntity<FilialDto> listarUnico(@PathVariable UUID id) {
		Optional<Filial> filialOpt = this.filialRep.findById(id);
		if (filialOpt.isPresent()) {
			Filial filial = filialOpt.get();
			return ResponseEntity.ok(new FilialDto(filial));
		}

		return ResponseEntity.notFound().build();
	}

	// Cadastrar funcionario
	@PostMapping
	public ResponseEntity<FilialDto> cadastrar(@RequestBody @Valid FilialForm filialForm, UriComponentsBuilder uriBuilder) {
		Filial filial = filialForm.converter();
		this.filialRep.save(filial);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(filial.getId()).toUri();
		return ResponseEntity.created(uri).body(new FilialDto(filial));
	}

	// Editar funcionario
	@PutMapping("/{id}")
	public ResponseEntity<FilialDto> atualizar(@PathVariable UUID id, @RequestBody @Valid FilialForm filialForm) {
		Optional<Filial> filialOpt = this.filialRep.findById(id);
		if (filialOpt.isPresent()) {
			Filial filial = filialForm.atualizar(filialOpt);
			return ResponseEntity.ok(new FilialDto(filial));
		}

		return ResponseEntity.notFound().build();
	} 

	// Deletar funcionario
	@DeleteMapping("/{id}")
	public ResponseEntity<FilialDto> remover(@PathVariable UUID id) {
		Optional<Filial> filialOpt = this.filialRep.findById(id);
		if (filialOpt.isPresent()) {
			this.filialRep.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
