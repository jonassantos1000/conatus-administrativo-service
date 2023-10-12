package br.com.app.conatus.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.entities.ClienteEntity;
import br.com.app.conatus.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ClienteController {
	
	private final ClienteRepository clienteRepository;

	@PostMapping("/teste")
	public ClienteEntity buscarClientes() {
		return clienteRepository.findById(2L).orElse(null);
	}	
	
} 
