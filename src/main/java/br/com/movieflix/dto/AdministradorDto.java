package br.com.movieflix.dto;

import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.Filial;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdministradorDto implements LoginDto{

	private UUID id;
	private String nome;
	private String email;
	private Filial filialId;

	public AdministradorDto(Administrador administrador) {
		this.id = administrador.getId();
		this.nome = administrador.getNome();
		this.email = administrador.getEmail();
		this.filialId = administrador.getFilialId();
	}

	public static Page<AdministradorDto> converter(Page<Administrador> administradores) {
		return administradores.map(AdministradorDto::new);
	}

}
