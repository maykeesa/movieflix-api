package br.com.movieflix.controller;

import java.net.URI;

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

import br.com.movieflix.dto.UsuarioDto;
import br.com.movieflix.form.UsuarioForm;
import br.com.movieflix.form.att.UsuarioAttForm;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService userService;

	// Lista todos os usuarios
	@GetMapping
	public Page<UsuarioDto> listarTodos(
			@PageableDefault(sort = "cpf", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return userService.pageUsuario(paginacao);
	}

	// Lista usuario por Id
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> listarUnico(@PathVariable String id) {
		if (this.userService.isUsuarioPresent(id)) {
			Usuario user = this.userService.getUsuarioById(id);
			return ResponseEntity.ok(new UsuarioDto(user));
		}

		return ResponseEntity.notFound().build();
	}

	// Cadastrar usuario
	@PostMapping
	public ResponseEntity<UsuarioDto> cadastrar(@RequestBody @Valid UsuarioForm usuarioForm,
			UriComponentsBuilder uriBuilder) {
		Usuario user = usuarioForm.converterToModel();
		URI uri = this.userService.cadastrar(user, uriBuilder);
		return ResponseEntity.created(uri).body(new UsuarioDto(user));
	}

	// Editar usuario
	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> atualizar(@PathVariable String id, @RequestBody @Valid UsuarioAttForm userAttForm) {
		if (this.userService.isUsuarioPresent(id)) {
			Usuario user = this.userService.atualizar(id, userAttForm);
			return ResponseEntity.ok(new UsuarioDto(user));
		}

		return ResponseEntity.notFound().build();
	}

	// Deletar usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDto> remover(@PathVariable String id) {
		if (this.userService.isUsuarioPresent(id)) {
			this.userService.deletarUsuarioById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
