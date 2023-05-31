package br.com.movieflix.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.EstoqueDto;
import br.com.movieflix.form.EstoqueForm;
import br.com.movieflix.model.Estoque;
import br.com.movieflix.model.Filial;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.ProdutoRepository;
import br.com.movieflix.service.EstoqueService;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
	
	@Autowired
	private EstoqueService estoqueService;
	
	@Autowired
	private FilialRepository filialRep;
	
	@Autowired
	private ProdutoRepository produtoRep;

	// Lista todo o estoque
	@GetMapping
	public Page<EstoqueDto> listarTodos(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.estoqueService.pageEstoque(paginacao);
	}

	// Lista estoque por filialId
	@GetMapping("/{filialId}")
	public ResponseEntity<List<Estoque>> listarUnico(@PathVariable UUID filialId) {
		Optional<Filial> filialOpt = this.filialRep.findById(filialId);
		if (filialOpt.isPresent()) {
			List<Estoque> estoqueFilial = this.estoqueService.getEstoqueByFilialId(filialOpt.get());
			return ResponseEntity.ok(estoqueFilial);
		}
		return ResponseEntity.notFound().build();
	}

	// Cadastrar estoque
	@PostMapping
	public ResponseEntity<EstoqueDto> cadastrar(@RequestBody @Valid EstoqueForm estoqueForm,
			UriComponentsBuilder uriBuilder) {
		Estoque estoque = estoqueForm.converterToModel(this.produtoRep, this.filialRep);
		URI uri = this.estoqueService.cadastrar(estoque, uriBuilder);
		return ResponseEntity.created(uri).body(new EstoqueDto(estoque));
	}

	// Deletar estoque
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id) {
		if (this.estoqueService.isEstoquePresent(id)) {
			this.estoqueService.deletarEstoqueById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
