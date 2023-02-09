package br.com.movieflix.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Funcionario;
import br.com.movieflix.repository.FilialRepository;
import lombok.Getter;

@Getter
public class FuncionarioForm {

	@NotBlank @NotNull @NotEmpty
	private String nome;
	@Email
	private String email;
	@NotBlank @NotNull @NotEmpty
	private String senha;
	@NotNull
	private Long filialId; 
	
	public Funcionario converter(FilialRepository filialRep) {
		Filial filial = filialRep.findById(filialId).get();
		return new Funcionario(this.nome, this.email, this.senha, filial);
	}
	
}
