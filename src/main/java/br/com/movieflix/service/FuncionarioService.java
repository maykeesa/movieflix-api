package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.model.Funcionario;
import br.com.movieflix.model.dto.FuncionarioDto;
import br.com.movieflix.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcRep;
	
	// Retorna se funcionario existe ou nao, pelo Id
	public boolean isIdFuncionarioPresent(Long id) {
		Optional<Funcionario> funcOpt = this.funcRep.findById(id);
		if(funcOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna funcionario por Id
	public Funcionario getFuncionarioById(Long id) {
		return this.funcRep.findById(id).get();
	}
	
	// Paginacao de funcionarios
	public Page<FuncionarioDto> pageFuncionario(Pageable paginacao) {
		Page<Funcionario> funcionarios = this.funcRep.findAll(paginacao);
		return FuncionarioDto.converter(funcionarios);
	}
	
	// Cadastra funcionario
	public URI cadastrar(Funcionario func, UriComponentsBuilder uriBuilder) {
		this.funcRep.save(func);
		return uriBuilder.path("/topicos/{id}").buildAndExpand(func.getId()).toUri();
	}

	// Cadastrar funcionario como gerente
	public void cadastrarGerente(Long id) {
		Funcionario func = this.getFuncionarioById(id);
		func.setGerent(true);
		this.funcRep.save(func);
	}
	
	// Deletar funcionario
	public void deletarFuncionarioById(Long id) {
		this.funcRep.deleteById(id);
	}
}
