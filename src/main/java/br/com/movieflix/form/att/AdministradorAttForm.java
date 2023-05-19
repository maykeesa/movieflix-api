package br.com.movieflix.form.att;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Administrador;
import lombok.Setter;

@Setter
public class AdministradorAttForm {

    @NotBlank @NotNull @NotEmpty
    private String nome;
    @NotBlank @NotNull @NotEmpty
    private String email;
    @NotBlank @NotNull @NotEmpty
    private String senha;
    
    public Administrador atualizar(Administrador administrador) {
        administrador.setNome(this.nome);
        administrador.setEmail(this.email);
        administrador.setSenha(this.senha);
        return administrador;
    }
}
