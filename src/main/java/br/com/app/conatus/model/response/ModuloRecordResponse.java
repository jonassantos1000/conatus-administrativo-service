package br.com.app.conatus.model.response;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record ModuloRecordResponse(
		Long id,
		String descricao,
		List<FuncionalidadeResponse> funcionalidades,
		BigDecimal valor,
		List<ModuloRecordResponse> subModulos
		) {
}
