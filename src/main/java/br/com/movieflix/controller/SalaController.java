package br.com.movieflix.controller;

import java.net.URI;
import java.util.List;
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

import br.com.movieflix.dto.SalaDto;
import br.com.movieflix.form.SalaForm;
import br.com.movieflix.form.att.SalaAttForm;
import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Sala;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.service.FilialService;
import br.com.movieflix.service.SalaService;

@RestController
@RequestMapping("/sala")
public class SalaController {
	
	@Autowired
	private SalaService salaService;

	@Autowired
	private FilialService filialService;
	
	@Autowired
	private FilialRepository filialRep;

	// Lista todas as salas
	@GetMapping
	public Page<SalaDto> listarTodos(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.salaService.pageSala(paginacao);
	}

	// Lista por Id sala
	@GetMapping("/{id}")
	public ResponseEntity<SalaDto> listarUnico(@PathVariable UUID id) {
		if (this.salaService.isSalaPresent(id)) {
			Sala sala = this.salaService.getSalaById(id);
			return ResponseEntity.ok(new SalaDto(sala));
		}

		return ResponseEntity.notFound().build();
	}
	
	// Lista sala por filial
	@GetMapping("/filial/{filialId}")
	public ResponseEntity<List<SalaDto>> listarSalaFilial(@PathVariable String filialId) {
		UUID filialIdConvertido = UUID.fromString(filialId);
		
		if(this.filialService.isFilialPresent(filialIdConvertido)) {
			Filial filial = this.filialService.getFilialById(filialIdConvertido);
			return ResponseEntity.ok(salaService.getSalaByFilialId(filial));			
		}
		return ResponseEntity.notFound().build();
	}

	// Cadastrar sala
	@PostMapping
	public ResponseEntity<SalaDto> cadastrar(@RequestBody @Valid SalaForm salaForm, UriComponentsBuilder uriBuilder) {
		Sala sala = salaForm.converterToModel(this.filialRep);
		URI uri = this.salaService.cadastrar(sala, uriBuilder);
		return ResponseEntity.created(uri).body(new SalaDto(sala));
	}
	
	// Mudar status da sala
	@PostMapping("/{id}/status/{status}")
	public ResponseEntity<SalaDto> mudarStatus(@PathVariable UUID id, @PathVariable boolean status){
		if(this.salaService.isSalaPresent(id)) {
			this.salaService.mudarStatus(id, status);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}

	// Editar sala
	@PutMapping("/{id}")
	public ResponseEntity<SalaDto> atualizar(@PathVariable UUID id, @RequestBody @Valid SalaAttForm salaAttForm) {
		if (this.salaService.isSalaPresent(id)) {
			Sala sala = this.salaService.atualizar(id, salaAttForm);
			return ResponseEntity.ok(new SalaDto(sala));
		}

		return ResponseEntity.notFound().build();
	} 

	// Deletar sala
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable UUID id) {
		if (this.salaService.isSalaPresent(id)) {
			this.salaService.deletarSalaById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
