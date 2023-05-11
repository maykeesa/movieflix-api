package br.com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
