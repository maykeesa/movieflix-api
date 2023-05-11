package br.com.movieflix.model.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.movieflix.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioDto {

	private String cpf;
	private String nome;
	private String email;
	private String dataNascimento;
	private LocalDateTime contaCriada;
	
	public UsuarioDto(Usuario user) {
		this.cpf = user.getCpf();
		this.nome = user.getNome();
		this.email = user.getEmail();
		this.dataNascimento = user.getDataNascimento();
		this.contaCriada = user.getContaCriada();
	}
	
	public static Page<UsuarioDto> converter(Page<Usuario> users){
		return users.map(UsuarioDto::new);
	}
}
