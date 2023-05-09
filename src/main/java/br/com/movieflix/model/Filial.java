package br.com.movieflix.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Filial {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String cep;
	private String endereco;
	private String numero;
	private String bairro;
	private String cidade;
	private String uf;
	
	public Filial(String cep, String endereco, String numero, String bairro, String cidade, String uf) {
		this.cep = cep;
		this.endereco = endereco;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}
}
