package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.infra.exceptions.NaoEncontradoException;
import br.com.app.conatus.repositories.DominioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DominioService {
	
	private final DominioRepository dominioRepository;
	
	public DominioEntity recuperarPorId(Long id) {
		return dominioRepository.findById(id).orElseThrow(
				() -> new NaoEncontradoException("Não foi encontrado um dominio com id: %d".formatted(id)));
	}
	
	public DominioEntity recuperarPorCodigo(CodigoDominio codigo) {
		return dominioRepository.findByCodigo(codigo.name()).orElseThrow(
				() -> new NaoEncontradoException("Não foi encontrado um dominio com codigo: %s".formatted(codigo.name())));
	}

}
