package br.com.app.conatus.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.response.ModuloRecordResponse;
import br.com.app.conatus.service.ModuloService;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/modulos")
@RequiredArgsConstructor
public class ModuloController {
	
	private final ModuloService moduloService;
	
	@GetMapping
	public List<ModuloRecordResponse> buscarModulos() {
		return moduloService.pesquisarModulos();
	}
	
	@GetMapping("/{idModulo}")
	public ModuloRecordResponse buscarModulos(@PathVariable Long idModulo) {
		return moduloService.pesquisarModulosPorId(idModulo);
	}


}
