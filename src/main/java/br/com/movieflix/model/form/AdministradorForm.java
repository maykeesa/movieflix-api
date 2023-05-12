package br.com.movieflix.model.form;

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.Filial;
import br.com.movieflix.repository.FilialRepository;
import lombok.Getter;

@Getter
public class AdministradorForm {
	
    @NotBlank @NotEmpty
    private String nome;
    @Email
    private String email;
    @NotBlank @NotEmpty
    private String senha;
    @NotNull
    private UUID filialId;

    public Administrador converter(FilialRepository filialRep) {
    	Filial filial = filialRep.findById(this.filialId).get();
        return new Administrador(this.nome, this.email, this.senha, filial);
    }
}
