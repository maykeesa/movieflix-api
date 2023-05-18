package br.com.movieflix.model.form;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Reserva;
import br.com.movieflix.model.Sesao;
import br.com.movieflix.repository.CompraRepository;
import br.com.movieflix.repository.SesaoRepository;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class ReservaForm {

    @NotNull
    private UUID compraId;
    @NotNull
    private UUID sesaoId;

    public Reserva converterToModel(CompraRepository CompraRepository, SesaoRepository SesaoRepository) {
        Compra compra = CompraRepository.findById(compraId).get();
        Sesao sesao = SesaoRepository.findById(sesaoId).get();
        return new Reserva(compra, sesao);
    }

    public Reserva atualizar(Reserva reserva, CompraRepository CompraRepository, SesaoRepository SesaoRepository) {
        Compra compra = CompraRepository.findById(compraId).get();
        Sesao sesao = SesaoRepository.findById(sesaoId).get();
        reserva.setCompraId(compra);
        reserva.setSesaoId(sesao);
        return reserva;
    }



}
