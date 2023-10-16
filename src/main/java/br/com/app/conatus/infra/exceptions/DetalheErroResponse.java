package br.com.app.conatus.infra.exceptions;

import org.springframework.validation.FieldError;

public record DetalheErroResponse(String tipo, String mensagem) {
	public DetalheErroResponse(FieldError erro) { 
		this(erro.getField(), erro.getDefaultMessage());
	}
}
