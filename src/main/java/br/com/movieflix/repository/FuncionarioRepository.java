package br.com.movieflix.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>{

	public Optional<Funcionario> findByEmail(String email);
	
	public List<Funcionario> findByFilialId(Filial filial);
}
