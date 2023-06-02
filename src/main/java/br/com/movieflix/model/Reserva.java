package br.com.movieflix.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String cadeira;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Compra compraId;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Sessao sessaoId;

	public Reserva(String cadeira, Compra compraId, Sessao sesaoId) {
		this.cadeira = cadeira;
		this.compraId = compraId;
		this.sessaoId = sesaoId;
	}
}
