package br.com.movieflix.controller;

import java.net.URI;
import java.util.List;
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

import br.com.movieflix.dto.CatalogoDto;
import br.com.movieflix.form.CatalogoForm;
import br.com.movieflix.model.Catalogo;
import br.com.movieflix.model.Filial;
import br.com.movieflix.repository.FilialRepository;
import br.com.movieflix.repository.FilmeRepository;
import br.com.movieflix.service.CatalogoService;
import br.com.movieflix.service.FilialService;

@RestController
@RequestMapping("/catalogo")
public class CatalogoController {

	@Autowired
	private CatalogoService catalogoService;
	
	@Autowired
	private FilialService filialService;
	
	@Autowired
	private FilmeRepository filmeRep;
	
	@Autowired
	private FilialRepository filialRep;

	// Lista todos os catalogos
	@GetMapping
	public Page<CatalogoDto> listarTodos(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		return this.catalogoService.pageCatalogo(paginacao);
	}

	// Lista catalogo de filial
	@GetMapping("/{filialId}")
	public ResponseEntity<List<Catalogo>> listarCatalogoFilial(@PathVariable String filialId) {
		UUID filialIdConvertido = UUID.fromString(filialId);
		
		if(this.filialService.isFilialPresent(filialIdConvertido)) {
			Filial filial = this.filialService.getFilialById(filialIdConvertido);
			return ResponseEntity.ok(catalogoService.getCatalogoByFilialId(filial));			
		}
		return ResponseEntity.notFound().build();
	}

	// Cadastrar catalogo
	@PostMapping
	public ResponseEntity<CatalogoDto> cadastrar(@RequestBody @Valid CatalogoForm catalogoForm,
			UriComponentsBuilder uriBuilder) {
		Catalogo catalogo = catalogoForm.converterToModel(this.filmeRep, this.filialRep);
		URI uri = this.catalogoService.cadastrar(catalogo, uriBuilder);
		return ResponseEntity.created(uri).body(new CatalogoDto(catalogo));
	}

	// Deletar catalogo
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable UUID id) {
		if (this.catalogoService.isCatalogoPresent(id)) {
			this.catalogoService.deletarCatalogoById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
