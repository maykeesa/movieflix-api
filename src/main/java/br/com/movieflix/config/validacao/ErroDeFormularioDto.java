package br.com.movieflix.config.validacao;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class ErroDeFormularioDto {

	private Long status;
	private String campo;
	private String erro;
	private LocalDateTime timeStamp;

	public ErroDeFormularioDto(Long status, String campo, String erro, LocalDateTime timeStamp) {
		this.status = status;
		this.campo = campo;
		this.erro = erro;
		this.timeStamp = timeStamp;
	}

}
