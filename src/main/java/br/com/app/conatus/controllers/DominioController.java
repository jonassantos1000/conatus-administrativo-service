package br.com.app.conatus.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.commons.model.response.DominioResponse;
import br.com.app.conatus.services.DominioService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/dominios")
@RequiredArgsConstructor
public class DominioController {
	
	private final DominioService dominioService;
	
	
	@GetMapping("/{id}")
	public DominioResponse buscarDominioPorId(@PathVariable Long id) {
		return dominioService.buscarDominioPorId(id);
	}
	
	@GetMapping("/codigos/{codDominio}")
	public DominioResponse buscarDominioPorCodigo(@PathVariable String codDominio) {
		return dominioService.buscarDominioPorCodigo(codDominio);
	}
	
	@GetMapping("/tipos-id/{idTipo}")
	public List<DominioResponse> buscarDominioPorCodigoTipo(@PathVariable Long idTipo) {
		return dominioService.buscarDominioPorIdTipo(idTipo);
	}
	
	@GetMapping("/tipos-codigos/{codTipo}")
	public List<DominioResponse> buscarDominioPorCodigoTipo(@PathVariable String codTipo) {
		return dominioService.buscarDominioPorCodigoTipo(codTipo);
	}

}
