package br.com.app.conatus.model.response;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record FuncionalidadeResponse(
		
		Long id,
		String descricao,
		BigDecimal valor,
		String codigoTipo,
		String descricaoTipo,
		String modulo
		) {

}
