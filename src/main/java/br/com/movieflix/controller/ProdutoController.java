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

import br.com.movieflix.model.Produto;
import br.com.movieflix.model.dto.ProdutoDto;
import br.com.movieflix.model.form.ProdutoForm;
import br.com.movieflix.model.form.att.ProdutoAttForm;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.service.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private FilialRepository filialRep;

	// Lista todos os produto
	@GetMapping
	public Page<ProdutoDto> listarTodos(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return produtoService.pageProduto(paginacao);
	}

	// Lista produto por Id
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoDto> listarUnico(@PathVariable UUID id) {
		if (this.produtoService.isProdutoPresent(id)) {
			Produto produto = this.produtoService.getProdutoById(id);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}

		return ResponseEntity.notFound().build();
	}

	// Cadastrar produto
	@PostMapping
	public ResponseEntity<ProdutoDto> cadastrar(@RequestBody @Valid ProdutoForm produtoForm,
			UriComponentsBuilder uriBuilder) {
		Produto produto = produtoForm.converter(this.filialRep);
		URI uri = this.produtoService.cadastrar(produto, uriBuilder);
		return ResponseEntity.created(uri).body(new ProdutoDto(produto));
	}

	// Editar produto
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDto> atualizar(@PathVariable UUID id,
			@RequestBody @Valid ProdutoAttForm produtoAttForm) {
		if (this.produtoService.isProdutoPresent(id)) {
			Produto produto = this.produtoService.atualizar(id, produtoAttForm);
			return ResponseEntity.ok(new ProdutoDto(produto));
		}

		return ResponseEntity.notFound().build();
	}

	// Deletar produto
	@DeleteMapping("/{id}")
	public ResponseEntity<ProdutoDto> remover(@PathVariable UUID id) {
		if (this.produtoService.isProdutoPresent(id)) {
			this.produtoService.deletarProdutoById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
