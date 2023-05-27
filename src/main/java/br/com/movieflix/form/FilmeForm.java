package br.com.movieflix.form;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.movieflix.model.Filme;
import br.com.movieflix.service.DateService;
import lombok.Getter;

@Getter
public class FilmeForm {

	@NotBlank @NotEmpty
    private String nome;
    @NotNull
    private int nota;
    @NotBlank @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String sinopse;
    @NotBlank @NotEmpty
    private String diretor;
    private String srcCapa;
    @NotBlank @NotEmpty
    private String dataLancamento;

    public Filme converterToModel() {
    	LocalDateTime dataLancamento = DateService.dataStringToClass(this.dataLancamento);
    	if(this.srcCapa == "") {
    		return new Filme(this.nome, this.nota, this.sinopse, this.diretor, dataLancamento);    		
    	}
    	return new Filme(this.nome, this.nota, this.sinopse, this.diretor, this.srcCapa, dataLancamento);   
    }

    public Filme atualizar(Filme filme) {
    	LocalDateTime dataLancamento = DateService.dataStringToClass(this.dataLancamento);
    	filme.setNome(this.nome);
        filme.setNota(this.nota);
        filme.setSinopse(this.sinopse);
        filme.setDiretor(this.diretor);
        filme.setDataLancamento(dataLancamento);
        return filme;
    }
}
