package br.com.movieflix.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;

@Getter
public class LoginForm {

	@Email
	public String email;
	@NotBlank @NotEmpty
	public String senha;
	
	public LoginForm(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}
}
