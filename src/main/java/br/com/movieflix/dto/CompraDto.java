package br.com.movieflix.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import br.com.movieflix.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompraDto {

	private UUID id;
	private LocalDateTime dataCompra;
	private Usuario usuario;
	private ArrayList<Produto> produtos;
	
	public CompraDto(Compra compra) {
		this.id = compra.getId();
		this.dataCompra = compra.getDataCompra();
		this.usuario = compra.getUsuario();
		this.produtos = compra.getProdutos();
	}
	
	public static Page<CompraDto> converter(Page<Compra> compras){
		return compras.map(CompraDto::new);
	}
	
}
