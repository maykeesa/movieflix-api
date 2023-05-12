package br.com.movieflix.model.dto;

import br.com.movieflix.model.Administrador;
import org.springframework.data.domain.Page;

import java.util.UUID;

public class AdministradorDto {

    private UUID id;
    private String nome;
    private String email;
    private String senha;
    private String filialId;

    public AdministradorDto(Administrador administrador) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.filialId = filialId;
    }



    public static Page<AdministradorDto> converter(Page<Administrador> administradores){
        return administradores.map(AdministradorDto::new);
    }

}
