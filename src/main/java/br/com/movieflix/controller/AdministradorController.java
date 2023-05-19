package br.com.movieflix.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

import br.com.movieflix.dto.AdministradorDto;
import br.com.movieflix.form.AdministradorForm;
import br.com.movieflix.form.att.AdministradorAttForm;
import br.com.movieflix.model.Administrador;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.service.AdministradorService;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

	@Autowired
	private AdministradorService administradorService;
	
	@Autowired
	private FilialRepository filialRep;

	// Lista todas os administradores
	@GetMapping
	public Page<AdministradorDto> listarTodos(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.administradorService.pageAdministrador(paginacao);
	}

	// Lista por Id administrador
	@GetMapping("/{id}")
	public ResponseEntity<AdministradorDto> listarUnico(@PathVariable UUID id) {
		if (this.administradorService.isAdministradorPresent(id)) {
			Administrador administrador = this.administradorService.getAdministradorById(id);
			return ResponseEntity.ok(new AdministradorDto(administrador));
		}
		return ResponseEntity.notFound().build();
	}

	// Cadastrar administrador
	@PostMapping
	public ResponseEntity<AdministradorDto> cadastrar(@RequestBody @Valid AdministradorForm administradorForm,
			UriComponentsBuilder uriBuilder) {
		Administrador administrador = administradorForm.converterToModel(this.filialRep);
		URI uri = this.administradorService.cadastrar(administrador, uriBuilder);
		return ResponseEntity.created(uri).body(new AdministradorDto(administrador));
	}

	// Editar administrador
	@PutMapping("/{id}")
	public ResponseEntity<AdministradorDto> editar(@PathVariable UUID id, @RequestBody @Valid AdministradorAttForm administradorAttForm) {
		if (this.administradorService.isAdministradorPresent(id)) {
			Administrador administrador = this.administradorService.atualizar(id, administradorAttForm);
			return ResponseEntity.ok(new AdministradorDto(administrador));
		}
		return ResponseEntity.notFound().build();
	}

	// Deletar administrador
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id) {
		if (this.administradorService.isAdministradorPresent(id)) {
			this.administradorService.deletarAdministratorById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
