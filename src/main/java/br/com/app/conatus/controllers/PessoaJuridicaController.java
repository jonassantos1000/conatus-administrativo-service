package br.com.app.conatus.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.PessoaJuridicaValidacaoRequest;
import br.com.app.conatus.service.PessoaJuridicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/pessoas-juridicas")
@RequiredArgsConstructor
public class PessoaJuridicaController {
	
	private final PessoaJuridicaService pessoaJuridicaService;
	
	@PostMapping("/validacao-cnpj")
	public void verificarPessoaJuridica(@Valid @RequestBody PessoaJuridicaValidacaoRequest dadosPj) {
		pessoaJuridicaService.verificarPessoaJuridica(dadosPj.cnpj());	
	}

}
