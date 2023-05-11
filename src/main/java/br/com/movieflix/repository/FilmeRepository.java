package br.com.movieflix.repository;

import br.com.movieflix.model.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FilmeRepository extends JpaRepository<Filme, UUID>{


}
