package br.com.movieflix.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public Optional<Usuario> findByEmail(String email);
}
