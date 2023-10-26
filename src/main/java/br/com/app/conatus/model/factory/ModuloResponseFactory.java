package br.com.app.conatus.model.factory;

import java.util.List;

import br.com.app.conatus.entities.ModuloEntity;
import br.com.app.conatus.model.response.ModuloRecordResponse;

public class ModuloResponseFactory {

	private ModuloResponseFactory() {}
	
	public static ModuloRecordResponse converterEntityParaRecord(ModuloEntity modulo) {
		return ModuloRecordResponse.builder()
				.id(modulo.getId())
				.descricao(modulo.getDescricao())
				.valor(modulo.getValorBase())
				.funcionalidades(FuncionalidadeResponseFactory.converterListEntityParaFuncionalidadeRecord(modulo.getFuncionalidades()))
				.build();
	}
	
	public static List<ModuloRecordResponse> converterListEntityParaListRecord(List<ModuloEntity> modulos) {
		return modulos.stream().map(ModuloResponseFactory::converterEntityParaRecord).toList();
	}
}
