package br.com.app.conatus.model.request;

import java.util.List;

import br.com.app.conatus.model.PessoaJuridicaRecord;
import br.com.app.conatus.model.UsuarioRecord;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoCadastroTenantRequest(
		
		@Valid
		PessoaJuridicaRecord pessoaJuridica,
		
		@Valid
		UsuarioRecord usuario,
		
		@NotNull(message = "O campo modulo é obrigatorio")
		List<ModuloRequest> modulos)
{

}
