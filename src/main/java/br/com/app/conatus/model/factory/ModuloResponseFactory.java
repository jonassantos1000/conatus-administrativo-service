package br.com.app.conatus.model.factory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import br.com.app.conatus.commons.entities.ModuloEntity;
import br.com.app.conatus.model.response.ModuloRecordResponse;

public class ModuloResponseFactory {

	private ModuloResponseFactory() {}
	
	public static ModuloRecordResponse converterEntityParaRecord(ModuloEntity modulo) {
	    return Optional.ofNullable(modulo)
	            .map(m -> {
	            	
	            	List<ModuloRecordResponse> subModulos = m.getModuloSubModulos().stream()
	                    .map(sub -> sub.getSubModulo())
	                    .filter(Objects::nonNull)
	                    .map(ModuloResponseFactory::converterEntityParaRecord)
	                    .toList();
	            	
	            	return ModuloRecordResponse.builder()
		                .id(m.getId())
		                .descricao(m.getDescricao())
		                .valor(m.getValorUnitario())
		                .funcionalidades(FuncionalidadeResponseFactory.converterListEntityParaFuncionalidadeRecord(m.getFuncionalidades()))
		                .subModulos(subModulos.isEmpty() ? null : subModulos)
		                .build();
	            })
	            .orElse(null);
	}
	
	public static List<ModuloRecordResponse> converterListEntityParaListRecord(List<ModuloEntity> modulos) {
		return modulos.stream().map(ModuloResponseFactory::converterEntityParaRecord).toList();
	}
}
