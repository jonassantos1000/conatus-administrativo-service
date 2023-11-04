package br.com.app.conatus.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.PessoaFisicaValidacaoRequest;
import br.com.app.conatus.service.PessoaFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/pessoas-fisicas")
@RequiredArgsConstructor
public class PessoaFisicaController {
	
	private final PessoaFisicaService pessoaFisicaService;
	
	@PostMapping("/validacao-cpf")
	public void verificarPessoaFisica(@Valid @RequestBody PessoaFisicaValidacaoRequest dadosPf) {
		pessoaFisicaService.verificarPessoaFisica(dadosPf.cpf());	
	}

}
