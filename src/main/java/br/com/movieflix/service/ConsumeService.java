package br.com.movieflix.service;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.movieflix.model.request.CEPRequest;

@Service
public class ConsumeService {

	public CEPRequest buscarCepById(String cep) {	
		RestTemplate restTemp = new RestTemplateBuilder()
				.rootUri("https://viacep.com.br/ws")
				.build();
		
		return restTemp.getForObject("/{cep}/json", CEPRequest.class, cep);
	}
}
