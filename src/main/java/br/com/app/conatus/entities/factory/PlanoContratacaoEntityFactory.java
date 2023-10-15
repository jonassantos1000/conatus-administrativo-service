package br.com.app.conatus.entities.factory;

import java.time.LocalDateTime;

import br.com.app.conatus.constantes.Constante;
import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.LicencaEntity;
import br.com.app.conatus.entities.PlanoContratacaoEntity;
import br.com.app.conatus.entities.TenantEntity;

public class PlanoContratacaoEntityFactory {

	private PlanoContratacaoEntityFactory() {}


	public static PlanoContratacaoEntity converterParaEntity(TenantEntity tenant, DominioEntity situacaoAtiva, LicencaEntity licenca) {
		LocalDateTime dataHoraAtual = LocalDateTime.now();
		
		return PlanoContratacaoEntity.builder()
				.dataContratacao(dataHoraAtual.atZone(Constante.ZONE_SP))
				.dataExpiracao(dataHoraAtual.plusDays(licenca.getDuracaoLicenca()).atZone(Constante.ZONE_SP))
				.situacao(situacaoAtiva)
				.tenant(tenant)
				.licenca(licenca)
				.build();
	}	
	
}
