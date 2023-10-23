package br.com.app.conatus.model.request;

import java.util.List;

import jakarta.validation.constraints.NotNull;

public record ModuloRequest(
		@NotNull(message = "O campo modulo é obrigatorio")
		Long idModulo,

		List<FuncionalidadeRequest> funcPremium) {

}
