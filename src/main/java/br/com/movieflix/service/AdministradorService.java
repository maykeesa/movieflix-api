package br.com.movieflix.service;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.movieflix.model.Administrador;
import br.com.movieflix.model.dto.AdministradorDto;
import br.com.movieflix.model.form.att.AdministradorAttForm;
import br.com.movieflix.repository.AdministradorRepository;

@Service
public class AdministradorService {

	@Autowired
	private AdministradorRepository administradorRep;

	// Retorna um booleano se administrador existe ou nao
	public boolean isAdministradorPresent(UUID id) {
		Optional<Administrador> administradorOpt = this.administradorRep.findById(id);
		if (administradorOpt.isPresent()) {
			return true;
		}
		return false;
	}

	// Retorna administrador por Id
	public Administrador getAdministradorById(UUID id) {
		Optional<Administrador> administradorOpt = this.administradorRep.findById(id);
		if (administradorOpt.isPresent()) {
			return administradorOpt.get();
		}
		return null;
	}

	// paginacao de administradores
	public Page<AdministradorDto> pageAdministrador(Pageable paginacao) {
		Page<Administrador> administradores = this.administradorRep.findAll(paginacao);
		return AdministradorDto.converter(administradores);
	}

	// Cadastra administrador
	public URI cadastrar(Administrador administrador, UriComponentsBuilder uriBuilder) {
		this.administradorRep.save(administrador);
		return uriBuilder.path("/administrador/{id}").buildAndExpand(administrador.getId()).toUri();
	}

	// Atualiza administrador
	public Administrador atualizar(UUID id, AdministradorAttForm administradorAttForm) {
		Administrador administrador = administradorAttForm.atualizar(this.getAdministradorById(id));
		this.administradorRep.save(administrador);
		return administrador;
	}

	// Deleta administrador
	public void deletarAdministratorById(UUID id) {
		this.administradorRep.deleteById(id);
	}
}
