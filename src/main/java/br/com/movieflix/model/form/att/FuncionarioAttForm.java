package br.com.movieflix.model.form.att;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Funcionario;
import lombok.Setter;

@Setter
public class FuncionarioAttForm {
	
	@NotBlank @NotNull @NotEmpty
	private String nome;
	@Email
	private String email;
	@NotBlank @NotNull @NotEmpty
	private String senha;

	public Funcionario atualizar(Funcionario func) {
		func.setNome(this.nome);
		func.setEmail(this.email);
		func.setSenha(senha);
		return func;
	}
}
