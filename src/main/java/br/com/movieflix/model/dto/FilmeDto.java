package br.com.movieflix.model.dto;


import br.com.movieflix.model.Filme;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FilmeDto {

    private UUID id;
    private String nome;
    private int nota;
    private String sinopse;
    private String diretor;
    private String dataLancamento;

    public FilmeDto(Filme filme) {
        this.id = filme.getId();
        this.nome = filme.getNome();
        this.nota = filme.getNota();
        this.sinopse = filme.getSinopse();
        this.diretor = filme.getDiretor();
        this.dataLancamento = filme.getDataLancamento();
    }

    public static Page<FilmeDto> converter(Page<Filme> filmes){
        return filmes.map(FilmeDto::new);
    }





}
