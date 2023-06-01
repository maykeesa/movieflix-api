package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.UsuarioDto;
import br.com.movieflix.form.att.UsuarioAttForm;
import br.com.movieflix.model.Usuario;
import br.com.movieflix.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository userRep;

	// Retorna um booleano se usuario existe ou nao
	public boolean isUsuarioPresent(String id) {
		Optional<Usuario> usuarioOpt = this.userRep.findById(id);
		if (usuarioOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna usuario por Id
	public Usuario getUsuarioByCpf(String cpf) {
		Optional<Usuario> usuarioOpt = this.userRep.findById(cpf);
		if (usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}
		return null;
	}
	
	// Retorna usuario por email
	public Usuario getUsuarioByEmail(String email) {
		Optional<Usuario> userOpt = this.userRep.findByEmail(email);
		if(userOpt.isPresent()){
			return userOpt.get();
		}
		return null;
	}

	// Paginacao de usuarios
	public Page<UsuarioDto> pageUsuario(Pageable paginacao) {
		Page<Usuario> usuarios = this.userRep.findAll(paginacao);
		return UsuarioDto.converter(usuarios);
	}

	// Cadastra usuario
	public URI cadastrar(Usuario user, UriComponentsBuilder uriBuilder) {
		this.userRep.save(user);
		return uriBuilder.path("/usuario/{cpf}").buildAndExpand(user.getCpf()).toUri();
	}

	// Atualiza usuario
	public Usuario atualizar(String cpf, UsuarioAttForm usuarioAttForm) {
		Usuario usuario = usuarioAttForm.atualizar(this.getUsuarioByCpf(cpf));
		this.userRep.save(usuario);
		return usuario;
	}

	// Deleta usuario
	public void deletarUsuarioById(String id) {
		this.userRep.deleteById(id);
	}
}
