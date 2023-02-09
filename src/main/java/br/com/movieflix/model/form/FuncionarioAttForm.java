package br.com.movieflix.model.form;

import java.util.Optional;

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

	public Funcionario atualizar(Long id, Optional<Funcionario> funcOpt) {
		Funcionario func = funcOpt.get();
		func.setNome(this.nome);
		func.setEmail(this.email);
		
		return func;
	}
}
