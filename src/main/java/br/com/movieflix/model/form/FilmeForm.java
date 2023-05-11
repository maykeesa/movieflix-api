package br.com.movieflix.model.form;

import br.com.movieflix.model.Filme;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FilmeForm {
    @NotBlank @NotNull @NotEmpty 
    private String nome ;
    @NotNull
    private int nota ;
    @Column(columnDefinition = "TEXT")
    private String sinopse ;
    private String diretor ;
    private String dataLancamento ;

    public Filme converter() {
        return new Filme(this.nome, this.nota, this.sinopse, this.diretor, this.dataLancamento);
    }

    public Filme atualizar(Filme filme) {
        filme.setNome(this.nome);
        filme.setNota(this.nota);
        filme.setSinopse(this.sinopse);
        filme.setDiretor(this.diretor);
        filme.setDataLancamento(this.dataLancamento);
        return filme;
    }


}
