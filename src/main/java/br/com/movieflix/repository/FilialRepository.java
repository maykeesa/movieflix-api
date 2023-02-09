package br.com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.movieflix.model.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long>{

}
