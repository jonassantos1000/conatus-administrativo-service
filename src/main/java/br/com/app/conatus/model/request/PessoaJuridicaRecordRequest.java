package br.com.app.conatus.model.request;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PessoaJuridicaRecordRequest(
		
		@NotBlank(message = "O campo nomeFantasia é obrigatorio")
		String nomeFantasia,
		
		@NotBlank(message = "O campo razaoSocial é obrigatorio")
		String razaoSocial,
		
		@CNPJ(message = "O CNPJ informado é invalido")
		String cnpj,
		
		@NotNull(message = "O campo idRamoAtividade é obrigatorio")
		Long idRamoAtividade ) {

}
