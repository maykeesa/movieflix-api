package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Catalogo;
import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Filme;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CatalogoDto {

	private UUID id;
	private Filme filme;
	private Filial filial;
	
	public CatalogoDto(Catalogo catalogo) {
		this.id = catalogo.getId();
		this.filme = catalogo.getFilmeId();
		this.filial = catalogo.getFilialId();
	}
	
	public static Page<CatalogoDto> converter(Page<Catalogo> catalogos){
		return catalogos.map(CatalogoDto::new);
	}
}
