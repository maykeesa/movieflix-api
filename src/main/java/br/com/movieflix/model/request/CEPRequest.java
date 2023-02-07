package br.com.movieflix.model.request;

import lombok.Data;

@Data
public class CEPRequest {

	private String cep;
	private String logradouro;
	private String bairro;
	private String localidade;
	private String uf;
}
