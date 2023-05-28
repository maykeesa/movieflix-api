package br.com.movieflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Filme;
import br.com.movieflix.model.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, UUID>{

	public List<Sessao> findByFilmeId(Filme filme);
}
