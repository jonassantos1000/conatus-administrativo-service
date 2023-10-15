package br.com.app.conatus.entities.factory;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.TenantEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.UsuarioTenantEntity;

public class UsuarioTenantEntityFactory {
	
	private UsuarioTenantEntityFactory() {}

	public static UsuarioTenantEntity converterParaEntity(UsuarioEntity solicitante, TenantEntity tenant, DominioEntity situacaoAtiva) {
		
		return UsuarioTenantEntity.builder()
				.usuario(solicitante)
				.tenant(tenant)
				.situacao(situacaoAtiva)
				.build();
	}

}
