package br.com.app.conatus.model.request;

import jakarta.validation.Valid;

public record SolicitacaoCadastroTenantRequest(
		
		@Valid
		PessoaJuridicaRecordRequest pessoaJuridica,
		
		@Valid
		UsuarioRecordRequest usuario)
{

}
