package br.com.movieflix.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.movieflix.model.Filial;
import lombok.Getter;

@Getter
public class FilialForm {

	@NotBlank @NotNull @NotEmpty @Size(min = 8, max = 8)
	private String cep;
	@NotBlank @NotNull @NotEmpty
	private String endereco;
	@NotBlank @NotNull @NotEmpty
	private String numero;
	@NotBlank @NotNull @NotEmpty
	private String bairro;
	@NotBlank @NotNull @NotEmpty
	private String cidade;
	@NotBlank @NotNull @NotEmpty
	private String uf;
	
	public Filial converter() {
		return new Filial(this.cep, this.endereco, this.numero, this.bairro, this.cidade, this.uf);
	}
	
	public Filial atualizar(Filial filial) {
		filial.setCep(this.cep);
		filial.setEndereco(this.endereco);
		filial.setNumero(this.numero);
		filial.setBairro(this.bairro);
		filial.setCidade(this.cidade);
		filial.setUf(this.uf);
		return filial;
	}
}
