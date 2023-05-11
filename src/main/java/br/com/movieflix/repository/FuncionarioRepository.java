package br.com.movieflix.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>{

}
