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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.ReservaDto;
import br.com.movieflix.form.ReservaForm;
import br.com.movieflix.model.Reserva;
import br.com.movieflix.repository.CompraRepository;
import br.com.movieflix.repository.SessaoRepository;
import br.com.movieflix.service.ReservaService;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;
    
    @Autowired
    private SessaoRepository sessaoRep;
    
    @Autowired
    private CompraRepository compraRep;

    // Lista todas as reservas
    @GetMapping
    public Page<ReservaDto> listarTodos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return this.reservaService.pageReserva(paginacao);
    }

    // Lista reserva por Id
    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> listarUnico(@PathVariable UUID id) {
        if (this.reservaService.isReservaPresent(id)) {
            Reserva reserva = this.reservaService.getReservaById(id);
            return ResponseEntity.ok(new ReservaDto(reserva));
        }

        return ResponseEntity.notFound().build();
    }

    // Cadastrar reserva
    @PostMapping
    public ResponseEntity<ReservaDto> cadastrar(@RequestBody @Valid ReservaForm reservaForm, UriComponentsBuilder uriBuilder) {
        Reserva reserva = reservaForm.converterToModel(this.compraRep, this.sessaoRep);
        URI uri = this.reservaService.cadastrar(reserva, uriBuilder);
        return ResponseEntity.created(uri).body(new ReservaDto(reserva));
    }

    // Deletar reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable UUID id) {
        if (this.reservaService.isReservaPresent(id)) {
            this.reservaService.deletar(id);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

}
