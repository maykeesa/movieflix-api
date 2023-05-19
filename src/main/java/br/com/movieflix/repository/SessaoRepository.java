package br.com.movieflix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, UUID>{

}
