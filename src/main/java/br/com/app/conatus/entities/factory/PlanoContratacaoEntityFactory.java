package br.com.app.conatus.entities.factory;

import java.time.LocalDateTime;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.PlanoContratacaoEntity;
import br.com.app.conatus.entities.TenantEntity;

public class PlanoContratacaoEntityFactory {

	private PlanoContratacaoEntityFactory() {}

	public static PlanoContratacaoEntity converterParaEntity(TenantEntity tenant, DominioEntity tipoLicenca,	DominioEntity situacaoAtiva, LocalDateTime dataExpiracao) {
		return PlanoContratacaoEntity.builder()
				.situacao(situacaoAtiva)
				.tenant(tenant)

				.build();
	}
	
	
}
