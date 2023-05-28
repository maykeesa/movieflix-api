package br.com.movieflix.repository;

import br.com.movieflix.model.Administrador;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdministradorRepository extends JpaRepository<Administrador, UUID>{
	
	public Optional<Administrador> findByEmail(String email);
}
