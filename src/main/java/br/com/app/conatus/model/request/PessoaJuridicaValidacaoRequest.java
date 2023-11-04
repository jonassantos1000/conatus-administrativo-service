package br.com.app.conatus.model.request;

import org.hibernate.validator.constraints.br.CNPJ;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PessoaJuridicaValidacaoRequest(
		
		@CNPJ(message = "CPF informado é inválido")
		@NotBlank(message = "Necessário informar o CNPJ para prosseguir com a verificação")
		String cnpj) {

}
