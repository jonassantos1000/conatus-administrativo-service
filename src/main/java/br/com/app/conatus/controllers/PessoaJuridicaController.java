package br.com.app.conatus.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/pessoas-juridicas")
public class PessoaJuridicaController {
	
	
	@PostMapping
	public void cadastrarPessoaJuridica(@RequestBody @Valid SolicitacaoCadastroTenantRequest pessoaJuridica) {
		
	}

}
