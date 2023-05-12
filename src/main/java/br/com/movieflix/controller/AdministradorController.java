package br.com.movieflix.controller;


import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.dto.AdministradorDto;
import br.com.movieflix.model.form.AdministradorForm;
import br.com.movieflix.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private AdministradorService administradorService;

    // Lista todas os administradores
    @GetMapping
    public Page<AdministradorDto> listarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return this.administradorService.pageAdministrador(paginacao);
    }

    // Lista  por Id administrador
    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDto> listarUnico(@PathVariable UUID id){
        if(this.administradorService.isAdministradorPresent(id)){
            Administrador administrador = this.administradorService.getAdministradorById(id);
            return ResponseEntity.ok(new AdministradorDto(administrador));
        }
        return ResponseEntity.notFound().build();
    }

    // Cadastrar administrador
    @PostMapping
    public ResponseEntity<AdministradorDto> cadastrar(@RequestBody @Valid AdministradorForm administradorForm, UriComponentsBuilder uriBuilder){
        Administrador administrador = administradorForm.converter();
        URI uri = this.administradorService.cadastrar(administrador, uriBuilder);
        return ResponseEntity.created(uri).body(new AdministradorDto(administrador));
    }

    // Editar administrador
    @PutMapping("/{id}")
    public ResponseEntity<AdministradorDto> editar(@PathVariable UUID id, @RequestBody @Valid AdministradorForm administradorForm){
        if(this.administradorService.isAdministradorPresent(id)){
            Administrador administrador = this.administradorService.atualizar(id, administradorForm);
            return ResponseEntity.ok(new AdministradorDto(administrador));
        }
        return ResponseEntity.notFound().build();
    }

    // Deletar administrador
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id){
        if(this.administradorService.isAdministradorPresent(id)){
            this.administradorService.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }








}
