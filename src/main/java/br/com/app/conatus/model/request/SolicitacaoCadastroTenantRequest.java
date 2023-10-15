package br.com.app.conatus.model.request;

import br.com.app.conatus.model.PessoaJuridica;
import br.com.app.conatus.model.UsuarioCadastro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoCadastroTenantRequest(
		
		@Valid
		PessoaJuridica pessoaJuridica,
		
		@Valid
		UsuarioCadastro usuario,
		
		@NotNull(message = "O campo plano é obrigatorio")
		Long idPlano) {

}
