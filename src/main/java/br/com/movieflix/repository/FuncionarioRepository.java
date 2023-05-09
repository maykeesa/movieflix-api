package br.com.movieflix.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Funcionario;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>{

}
