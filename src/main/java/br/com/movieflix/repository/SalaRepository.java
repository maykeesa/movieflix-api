package br.com.movieflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, UUID>{

	public List<Sala> findByFilialId(Filial filial);

}
