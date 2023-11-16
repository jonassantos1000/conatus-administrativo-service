package br.com.app.conatus.model.factory;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.model.response.DominioResponse;

public class DominioResponseFactory {

	private DominioResponseFactory() {}
	
	public static DominioResponse converterParaResponse(DominioEntity entity) {
		return DominioResponse.builder()
				.id(entity.getId())
				.descricao(entity.getDescricao())
				.codigo(entity.getCodigo())
				.build();
	}
}
