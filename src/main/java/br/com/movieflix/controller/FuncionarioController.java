package br.com.movieflix.controller;

import java.net.URI;
import java.util.Optional;

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

import br.com.movieflix.model.Funcionario;
import br.com.movieflix.model.dto.FuncionarioDto;
import br.com.movieflix.model.form.FuncionarioAttForm;
import br.com.movieflix.model.form.FuncionarioForm;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcRep;

	@Autowired
	private FilialRepository filialRep;

	// Lista todos os funcionarios
	@GetMapping
	public Page<FuncionarioDto> listarTodos(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		Page<Funcionario> funcionarios = this.funcRep.findAll(paginacao);
		return FuncionarioDto.converter(funcionarios);
	}

	// Lista funcionario por Id
	@GetMapping("/{id}")
	public ResponseEntity<FuncionarioDto> listarUnico(@PathVariable Long id) {
		Optional<Funcionario> funcOpt = this.funcRep.findById(id);
		if (funcOpt.isPresent()) {
			Funcionario fudc = funcOpt.get();
			return ResponseEntity.ok(new FuncionarioDto(fudc));
		}

		return ResponseEntity.notFound().build();
	}

	// Cadastrar funcionario
	@PostMapping
	public ResponseEntity<FuncionarioDto> cadastrar(@RequestBody @Valid FuncionarioForm funcForm,
			UriComponentsBuilder uriBuilder) {
		Funcionario func = funcForm.converter(this.filialRep);
		this.funcRep.save(func);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(func.getId()).toUri();
		return ResponseEntity.created(uri).body(new FuncionarioDto(func));
	}

	// Editar funcionario
	@PutMapping("/{id}")
	public ResponseEntity<FuncionarioDto> atualizar(@PathVariable Long id,
			@RequestBody @Valid FuncionarioAttForm funcForm) {
		Optional<Funcionario> funcOpt = funcRep.findById(id);
		if (funcOpt.isPresent()) {
			Funcionario func = funcForm.atualizar(id, funcOpt);
			return ResponseEntity.ok(new FuncionarioDto(func));
		}

		return ResponseEntity.notFound().build();
	} 

	// Deletar funcionario
	@DeleteMapping("/{id}")
	public ResponseEntity<FuncionarioDto> remover(@PathVariable Long id) {
		Optional<Funcionario> funcOpt = this.funcRep.findById(id);
		if (funcOpt.isPresent()) {
			this.funcRep.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
