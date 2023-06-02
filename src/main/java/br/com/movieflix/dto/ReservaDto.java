package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Reserva;
import br.com.movieflix.model.Sessao;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReservaDto {

	private UUID id;
	private String cadeira;
	private Compra compraId;
	private Sessao sessaoId;

	public ReservaDto(Reserva reserva) {
		this.id = reserva.getId();
		this.cadeira = reserva.getCadeira();
		this.compraId = reserva.getCompraId();
		this.sessaoId = reserva.getSessaoId();
	}

	public static Page<ReservaDto> converter(Page<Reserva> reservas) {
		return reservas.map(ReservaDto::new);
	}

}
