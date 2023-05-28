package br.com.movieflix.form;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Reserva;
import br.com.movieflix.model.Sessao;
import br.com.movieflix.repository.CompraRepository;
import br.com.movieflix.repository.SessaoRepository;
import lombok.Getter;

@Getter
public class ReservaForm {

	@NotNull
    private UUID compraId;
	@NotNull
    private UUID sessaoId;

    public Reserva converterToModel(CompraRepository compraRepository, SessaoRepository sessaoRepository) {
        Compra compra = compraRepository.findById(compraId).get();
        Sessao sessao = sessaoRepository.findById(sessaoId).get();
        return new Reserva(compra, sessao);
    }
}
