package br.com.app.conatus.entities.factory;

import java.time.LocalDateTime;

import br.com.app.conatus.commons.constantes.Constante;
import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.ModuloEntity;
import br.com.app.conatus.commons.entities.TenantEntity;
import br.com.app.conatus.commons.entities.TenantModuloEntity;

public class TenantModuloEntityFactory {

	private TenantModuloEntityFactory() {}

	public static TenantModuloEntity converterParaEntity(ModuloEntity modulo, TenantEntity tenant,
			DominioEntity situacaoAtiva, LocalDateTime dataExpiracao, boolean isPossuiFuncionalidadePremium) {

		return TenantModuloEntity.builder()
				.modulo(modulo)
				.tenant(tenant)
				.situacao(situacaoAtiva)
				.dataExpiracao(dataExpiracao.atZone(Constante.ZONE_SP))
				.flagPossuiCustom(isPossuiFuncionalidadePremium)
				.build();
	}
}
