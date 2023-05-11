package br.com.movieflix.model.form.att;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Usuario;
import lombok.Setter;

@Setter
public class UsuarioAttForm {
	
	@NotBlank @NotNull @NotEmpty
	private String nome;
	@Email
	private String email;
	@NotBlank @NotNull @NotEmpty
	private String senha;
	
	public Usuario atualizar(Usuario user) {
		user.setNome(this.nome);
		user.setEmail(this.email);
		user.setSenha(this.senha);
		return user;
	}
}
