package br.com.movieflix.service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.dto.SalaDto;
import br.com.movieflix.form.att.SalaAttForm;
import br.com.movieflix.model.Filial;
import br.com.movieflix.model.Sala;
import br.com.movieflix.repository.SalaRepository;

@Service
public class SalaService {

	@Autowired
	private SalaRepository salaRep;

	// Retorna um booleano se sala existe ou nao
	public boolean isSalaPresent(UUID id) {
		Optional<Sala> salaOpt = this.salaRep.findById(id);
		if (salaOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna sala por Id
	public Sala getSalaById(UUID id) {
		Optional<Sala> salaOpt = this.salaRep.findById(id);
		if (salaOpt.isPresent()) {
			return salaOpt.get();
		}
		return null;
	}
	
	// Retorna Lista de sala em Dto
	public List<SalaDto> getSalaByFilialId(Filial filial) {
		List<Sala> salas = this.salaRep.findByFilialId(filial);
		return this.listConverterToDto(salas);
	}
	
	// Converter lista to listaDto
	public List<SalaDto> listConverterToDto(List<Sala> salas){
		List<SalaDto> salaFormatado = new ArrayList<>();
		salas.forEach(i -> salaFormatado.add(new SalaDto(i)));
		return salaFormatado;
	}

	// paginacao de sala
	public Page<SalaDto> pageSala(Pageable paginacao) {
		Page<Sala> salas = this.salaRep.findAll(paginacao);
		return SalaDto.converter(salas);
	}

	// Cadastra sala
	public URI cadastrar(Sala sala, UriComponentsBuilder uriBuilder) {
		this.salaRep.save(sala);
		return uriBuilder.path("/sala/{id}").buildAndExpand(sala.getId()).toUri();
	}

	public void mudarStatus(UUID id, boolean status) {
		Sala sala = this.getSalaById(id);
		sala.setOcupada(status);
		this.salaRep.save(sala);
	}
	
	// Atualiza sala
	public Sala atualizar(UUID id, SalaAttForm salaAttForm) {
		Sala sala = salaAttForm.atualizar(this.getSalaById(id));
		this.salaRep.save(sala);
		return sala;
	}

	// Deleta sala
	public void deletarSalaById(UUID id) {
		this.salaRep.deleteById(id);
	}
}
