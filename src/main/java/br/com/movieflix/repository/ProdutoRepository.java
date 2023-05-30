package br.com.movieflix.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID>{

	public List<Produto> findByFilialId(Filial filial);
}
