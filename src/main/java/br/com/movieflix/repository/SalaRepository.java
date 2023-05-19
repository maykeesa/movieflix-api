package br.com.movieflix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, UUID>{

}
