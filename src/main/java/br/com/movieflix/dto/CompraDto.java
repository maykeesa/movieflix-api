package br.com.movieflix.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Compra;
import br.com.movieflix.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CompraDto {

	private UUID id;
	private LocalDateTime dataCompra;
	private UsuarioDto usuario;
	private List<Produto> produtosCompra;
	
	public CompraDto(Compra compra) {
		this.id = compra.getId();
		this.dataCompra = compra.getDataCompra();
		this.usuario = new UsuarioDto(compra.getUsuario());
		this.produtosCompra = compra.getProdutosCompra();
	}
	
	public static Page<CompraDto> converter(Page<Compra> compras){
		return compras.map(CompraDto::new);
	}
	
}
