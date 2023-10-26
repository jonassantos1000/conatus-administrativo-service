package br.com.app.conatus.model.request;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoCadastroTenantRequest(
		
		@Valid
		PessoaJuridicaRecordRequest pessoaJuridica,
		
		@Valid
		UsuarioRecordRequest usuario,
		
		@NotNull(message = "O campo modulo é obrigatorio")
		List<ModuloRequest> modulos)
{

}
