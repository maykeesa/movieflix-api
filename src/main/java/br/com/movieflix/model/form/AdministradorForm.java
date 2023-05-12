package br.com.movieflix.model.form;

import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.Filial;
import br.com.movieflix.service.AdministradorService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AdministradorForm {
    @NotBlank @NotNull @NotEmpty
    private String nome;
    @NotBlank @NotNull @NotEmpty
    private String email;
    @NotBlank @NotNull @NotEmpty
    private String senha;
    @NotNull
    private Filial filialId;

    public Administrador converter() {
        return new Administrador(this.nome, this.email, this.senha, this.filialId);
    }

    public Administrador atualizar(Administrador administrador) {
        administrador.setNome(this.nome);
        administrador.setEmail(this.email);
        administrador.setSenha(this.senha);
        administrador.setFilialId(this.filialId);
        return administrador;
    }



}
