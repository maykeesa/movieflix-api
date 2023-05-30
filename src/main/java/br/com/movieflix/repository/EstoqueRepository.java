package br.com.movieflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Estoque;
import br.com.movieflix.model.Produto;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID>{

	public List<Estoque> findByProdutoId(Produto produto);
}
