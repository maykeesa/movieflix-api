package br.com.movieflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Catalogo;
import br.com.movieflix.model.Filial;

public interface CatalogoRepository extends JpaRepository<Catalogo, UUID>{

	public List<Catalogo> findByFilialId(Filial filial);
}
