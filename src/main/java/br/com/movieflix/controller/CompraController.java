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

import br.com.movieflix.dto.CompraDto;
import br.com.movieflix.form.CompraForm;
import br.com.movieflix.form.att.CompraAttForm;
import br.com.movieflix.model.Compra;
import br.com.movieflix.repository.ProdutoRepository;
import br.com.movieflix.repository.UsuarioRepository;
import br.com.movieflix.service.CompraService;
import br.com.movieflix.service.ProdutoService;

@RestController
@RequestMapping("/compra")
public class CompraController {

	@Autowired
	private CompraService compraService;

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private UsuarioRepository userRep;
	
	@Autowired
	private ProdutoRepository produtoRep;

	// Lista todas as compras
	@GetMapping
	public Page<CompraDto> listarTodos(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.compraService.pageCompra(paginacao);
	}

	// Lista por Id compra
	@GetMapping("/{id}")
	public ResponseEntity<CompraDto> listarUnico(@PathVariable UUID id) {
		if (this.compraService.isCompraPresent(id)) {
			Compra compra = this.compraService.getCompraById(id);
			return ResponseEntity.ok(new CompraDto(compra));
		}
		return ResponseEntity.notFound().build();
	}

	// Cadastrar compra
	@PostMapping
	public ResponseEntity<CompraDto> cadastrar(@RequestBody @Valid CompraForm compraForm,
			UriComponentsBuilder uriBuilder) {
		Compra compra = compraForm.converterToModel(this.userRep, this.produtoRep, this.produtoService);
		URI uri = this.compraService.cadastrar(compra, uriBuilder);
		return ResponseEntity.created(uri).body(new CompraDto(compra));
	}

	// Editar compra
	@PutMapping("/{id}")
	public ResponseEntity<CompraDto> editar(@PathVariable UUID id,
			@RequestBody @Valid CompraAttForm compraAttForm) {
		if (this.compraService.isCompraPresent(id)) {
			Compra compra = this.compraService.atualizar(id, compraAttForm);
			return ResponseEntity.ok(new CompraDto(compra));
		}
		return ResponseEntity.notFound().build();
	}

	// Deletar compra
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id) {
		if (this.compraService.isCompraPresent(id)) {
			this.compraService.deletarCompraById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
