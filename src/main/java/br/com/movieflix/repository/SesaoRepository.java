package br.com.movieflix.repository;


import br.com.movieflix.model.Sesao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SesaoRepository extends JpaRepository<Sesao, UUID> {
}
