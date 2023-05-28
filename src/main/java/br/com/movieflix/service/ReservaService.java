package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.ReservaDto;
import br.com.movieflix.model.Reserva;
import br.com.movieflix.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRep;

    // Retorna um booleano se reserva existe ou nao
    public boolean isReservaPresent(UUID id) {
        Optional<Reserva> reservaOpt = this.reservaRep.findById(id);
        if(reservaOpt.isPresent()) {
            return true;
        }
        return false;
    }

    // Retorna reserva por Id
    public Reserva getReservaById(UUID id) {
        Optional<Reserva> reservaOpt = this.reservaRep.findById(id);
        if(reservaOpt.isPresent()){
            return reservaOpt.get();
        }
        return null;
    }

    // Paginacao de reservas
    public Page<ReservaDto> pageReserva(Pageable paginacao) {
        Page<Reserva> reservas = this.reservaRep.findAll(paginacao);
        return ReservaDto.converter(reservas);
    }

    // Cadastra reserva
    public URI cadastrar(Reserva reserva, UriComponentsBuilder uribuilder) {
        this.reservaRep.save(reserva);
        return uribuilder.path("/reserva/{id}").buildAndExpand(reserva.getId()).toUri();
    }


    // Deleta reserva
    public void deletar(UUID id) {
        this.reservaRep.deleteById(id);
    }




}
