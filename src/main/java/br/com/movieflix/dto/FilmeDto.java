package br.com.movieflix.dto;


import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Filme;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilmeDto {

    private UUID id;
    private String nome;
    private int nota;
    private String sinopse;
    private String diretor;
    private boolean dublado;
    private int faixaEtaria;
    private String srcCapa;
    private LocalDateTime dataLancamento;

    public FilmeDto(Filme filme) {
        this.id = filme.getId();
        this.nome = filme.getNome();
        this.nota = filme.getNota();
        this.sinopse = filme.getSinopse();
        this.diretor = filme.getDiretor();
        this.dublado = filme.isDublado();
        this.faixaEtaria = filme.getFaixaEtaria();
        this.srcCapa = filme.getSrcCapa();
        this.dataLancamento = filme.getDataLancamento();
    }

    public static Page<FilmeDto> converter(Page<Filme> filmes){
        return filmes.map(FilmeDto::new);
    }





}
