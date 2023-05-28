package br.com.movieflix.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.movieflix.dto.SessaoDto;
import br.com.movieflix.model.Filme;
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

	// Retorna uma lista de sessao de acordo com o filme
	public List<SessaoDto> listSessaoByFilme(Filme filme){
		List<SessaoDto> sessoesDto = new ArrayList<>();
		List<Sessao> sessoes = this.sessaoRep.findByFilmeId(filme);
		if(sessoes.size() != 0) {
			for(Sessao sessao : sessoes) {
				sessoesDto.add(new SessaoDto(sessao));
			}
		}
		
		return sessoesDto;
	}

	// paginacao de sessao
	public Page<SessaoDto> pageSessao(Pageable paginacao) {
		Page<Sessao> sessoes = this.sessaoRep.findAll(paginacao);
		return SessaoDto.converter(sessoes);
	}

	// Cadastra sessao
	public void cadastrar(Sessao sessao, List<String> horariosSessao) {
		List<LocalDateTime> horariosFormatados = this.listStringToLocalDateTime(horariosSessao);
		for(LocalDateTime data : horariosFormatados) {
			sessao.setHorarioSessao(data);
			this.sessaoRep.save(sessao);
		}
	}

	// Deleta sessao
	public void deletarSessaoById(UUID id) {
		this.sessaoRep.deleteById(id);
	}

	// Transforma uma lista de horarios em string, em uma de horarios em LocalDateTime
	public List<LocalDateTime> listStringToLocalDateTime(@NotBlank @NotEmpty List<String> horariosSessoes) {
		List<LocalDateTime> listaFormatada = new ArrayList<>();
		if(horariosSessoes.size() != 0) {
			horariosSessoes.forEach(i -> listaFormatada.add(DateService.dataSessaoToClass(i)));
			
			return listaFormatada;
		}
		
		return null;
	}
}
