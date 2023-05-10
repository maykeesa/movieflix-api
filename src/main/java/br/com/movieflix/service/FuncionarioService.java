package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.model.Funcionario;
import br.com.movieflix.model.dto.FuncionarioDto;
import br.com.movieflix.model.form.att.FuncionarioAttForm;
import br.com.movieflix.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcRep;

	// Retorna um booleano se funcionario existe ou nao
	public boolean isFuncionarioPresent(UUID id) {
		Optional<Funcionario> funcOpt = this.funcRep.findById(id);
		if(funcOpt.isPresent()) {
			return true;
		}
		return false;
	}
	
	// Retorna funcionario por Id
	public Funcionario getFuncionarioById(UUID id) {
		Optional<Funcionario> funcOpt = this.funcRep.findById(id);
		if(funcOpt.isPresent()){
			return funcOpt.get();
		}
		return null;
	}
	
	// Paginacao de funcionarios
	public Page<FuncionarioDto> pageFuncionario(Pageable paginacao) {
		Page<Funcionario> funcionarios = this.funcRep.findAll(paginacao);
		return FuncionarioDto.converter(funcionarios);
	}
	
	// Cadastra funcionario
	public URI cadastrar(Funcionario func, UriComponentsBuilder uriBuilder) {
		this.funcRep.save(func);
		return uriBuilder.path("/funcionario/{id}").buildAndExpand(func.getId()).toUri();
	}
	
	// Atualiza funcionario
	public Funcionario atualizar(UUID id, FuncionarioAttForm funcAttForm) {
		Funcionario func = funcAttForm.atualizar(this.getFuncionarioById(id));
		this.funcRep.save(func);
		return func;
	}

	// Cadastrar funcionario como gerente
	public void cadastrarGerente(UUID id) {
		// Fazer Verificação se já existe algum gerente naquela filial
		Funcionario func = this.getFuncionarioById(id);
		func.setGerent(true);
		this.funcRep.save(func);
	}
	
	// Deletar funcionario
	public void deletarFuncionarioById(UUID id) {
		this.funcRep.deleteById(id);
	}
}
