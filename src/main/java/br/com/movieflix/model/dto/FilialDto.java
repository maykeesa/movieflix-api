package br.com.movieflix.model.dto;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filial;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilialDto {

	private Long id;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	
	public FilialDto(Filial filial) {
		this.id = filial.getId();
		this.cep = filial.getCep();
		this.endereco = filial.getEndereco();
		this.numero = filial.getNumero();
		this.bairro = filial.getBairro();
		this.cidade = filial.getCidade();
		this.uf = filial.getUf();
	}
	
	public static Page<FilialDto> converter(Page<Filial> filiais){
		return filiais.map(FilialDto::new);
	}
}
