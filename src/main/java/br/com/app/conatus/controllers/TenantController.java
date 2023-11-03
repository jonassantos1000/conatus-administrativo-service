package br.com.app.conatus.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.service.TenantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/tenants")
@RequiredArgsConstructor
public class TenantController {
	
	private final TenantService tenantService;
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public void cadastrarTenant(@RequestBody @Valid SolicitacaoCadastroTenantRequest solicitacaoTenant) {
		tenantService.cadastrarTenant(solicitacaoTenant);
	}

}
