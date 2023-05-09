package br.com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Filial;

import java.util.UUID;

public interface FilialRepository extends JpaRepository<Filial, UUID>{

}
