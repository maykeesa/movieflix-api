package br.com.movieflix.dto;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProdutoDto {

	private UUID id;
	private String nome;
	private BigDecimal preco;
	private String srcSnack;
	
	public ProdutoDto(Produto produto) {
		this.id = produto.getId();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.srcSnack = produto.getSrcSnack();
	}
	
	public static Page<ProdutoDto> converter(Page<Produto> produtos){
		return produtos.map(ProdutoDto::new);
	}
}
