package br.com.app.conatus.model.request;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PessoaFisicaValidacaoRequest(
		
		@CPF(message = "CPF informado é inválido")
		@NotBlank(message = "Necessário informar o CPF para prosseguir com a verificação")
		String cpf) {

}
