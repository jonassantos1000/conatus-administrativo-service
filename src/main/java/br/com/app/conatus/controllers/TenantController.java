package br.com.app.conatus.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/tenant")
@RequiredArgsConstructor
public class TenantController {
	
	private final TenantService tenantService;
	
	@PostMapping
	public void cadastrarTenant(@RequestBody @Valid SolicitacaoCadastroTenantRequest solicitacaoTenant) {
		tenantService.cadastrarTenant(solicitacaoTenant);
	}

}
