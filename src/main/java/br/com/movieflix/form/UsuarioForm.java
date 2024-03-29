package br.com.movieflix.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import br.com.movieflix.model.Usuario;
import br.com.movieflix.service.DateService;
import lombok.Getter;

@Getter
public class UsuarioForm {

	@NotBlank @NotEmpty
	private String cpf;
	@NotBlank @NotEmpty
	private String nome;
	@Email
	private String email;
	@NotBlank @NotEmpty
	private String senha;
	@NotBlank @NotEmpty
	private String dataNascimento;
	
	public Usuario converterToModel() {
		LocalDateTime dataNasc = DateService.dataStringToClass(dataNascimento);
		return new Usuario(this.cpf, this.nome, this.email, this.senha, dataNasc);
	}
}
