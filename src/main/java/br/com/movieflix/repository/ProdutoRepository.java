package br.com.movieflix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

}
