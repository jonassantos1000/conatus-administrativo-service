package br.com.app.conatus.model.request;

import br.com.app.conatus.commons.model.request.UsuarioRecordRequest;
import jakarta.validation.Valid;

public record SolicitacaoCadastroTenantRequest(
		
		@Valid
		PessoaJuridicaRecordRequest pessoaJuridica,
		
		@Valid
		UsuarioRecordRequest usuario)
{

}
