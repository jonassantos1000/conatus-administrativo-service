package br.com.app.conatus.model.factory;

import java.util.List;
import java.util.Objects;

import br.com.app.conatus.entities.FuncionalidadeEntity;
import br.com.app.conatus.model.response.FuncionalidadeResponse;

public class FuncionalidadeResponseFactory {

	private FuncionalidadeResponseFactory() {}
	
	public static FuncionalidadeResponse converterEntityParaFuncionalidadeRecord(FuncionalidadeEntity func) {
		
		if (Objects.isNull(func)) {
			return null;
		}
		
		return FuncionalidadeResponse.builder()
				.id(func.getId())
				.descricao(func.getDescricao())
				.codigoTipo(func.getTipoFuncionalidade().getCodigo())
				.descricaoTipo(func.getTipoFuncionalidade().getDescricao())
				.valor(func.getValorFuncionalidade())
				.modulo(func.getModulo().getDescricao())
				.build();
	}
	
	public static List<FuncionalidadeResponse> converterListEntityParaFuncionalidadeRecord(List<FuncionalidadeEntity> funcionalidade) {
		
		return funcionalidade.stream().map(FuncionalidadeResponseFactory::converterEntityParaFuncionalidadeRecord).toList();
	}
}
