package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Estoque;
import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EstoqueDto {

	private UUID id;
	private Long quantidade;
	private Produto produto;
	private Filial filial;
	
	public EstoqueDto(Estoque estoque) {
		this.id = estoque.getId();
		this.quantidade = estoque.getQuantidade();
		this.produto = estoque.getProdutoId();
		this.filial = estoque.getFilialId();
	}
	
	public static Page<EstoqueDto> converter(Page<Estoque> estoque){
		return estoque.map(EstoqueDto::new);
	}
}
