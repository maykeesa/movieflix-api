package br.com.movieflix.model.dto;


import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Reserva;
import br.com.movieflix.model.Sesao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class ReservaDto {

    private UUID id;
    private Compra compraId;
    private Sesao sesaoId;

    public ReservaDto(Reserva reserva) {
        this.id = reserva.getId();
        this.compraId = reserva.getCompraId();
        this.sesaoId = reserva.getSesaoId();
    }

    public static Page<ReservaDto> converter(Page<Reserva> reservas){
        return reservas.map(ReservaDto::new);
    }





}
