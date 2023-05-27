package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.SessaoDto;
import br.com.movieflix.model.Sessao;
import br.com.movieflix.repository.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRep;

	// Retorna um booleano se sessao existe ou nao
	public boolean isSessaoPresent(UUID id) {
		Optional<Sessao> sessaoOpt = this.sessaoRep.findById(id);
		if (sessaoOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna sessao por Id
	public Sessao getSessaoById(UUID id) {
		Optional<Sessao> sessaoOpt = this.sessaoRep.findById(id);
		if (sessaoOpt.isPresent()) {
			return sessaoOpt.get();
		}
		return null;
	}

	// paginacao de sessao
	public Page<SessaoDto> pageSessao(Pageable paginacao) {
		Page<Sessao> sessoes = this.sessaoRep.findAll(paginacao);
		return SessaoDto.converter(sessoes);
	}

	// Cadastra sessao
	public URI cadastrar(Sessao sessao, UriComponentsBuilder uriBuilder) {
		this.sessaoRep.save(sessao);
		return uriBuilder.path("/sessao/{id}").buildAndExpand(sessao.getId()).toUri();
	}

	// Deleta sessao
	public void deletarSessaoById(UUID id) {
		this.sessaoRep.deleteById(id);
	}
}
