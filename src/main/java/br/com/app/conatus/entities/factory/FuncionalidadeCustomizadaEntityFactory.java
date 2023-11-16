package br.com.app.conatus.entities.factory;

import java.time.LocalDateTime;

import br.com.app.conatus.commons.constantes.Constante;
import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.FuncionalidadeEntity;
import br.com.app.conatus.commons.entities.TenantModuloEntity;
import br.com.app.conatus.commons.entities.TenantModuloFuncCustomEntity;

public class FuncionalidadeCustomizadaEntityFactory {

	private FuncionalidadeCustomizadaEntityFactory() {}

	public static TenantModuloFuncCustomEntity converterParaEntity(TenantModuloEntity tenantModulo,
			FuncionalidadeEntity func, DominioEntity situacao, LocalDateTime dtExpiracao) {

		return TenantModuloFuncCustomEntity.builder()
				.tenantModulo(tenantModulo)
				.funcionalidade(func)
				.situacao(situacao)
				.dataExpiracao(dtExpiracao.atZone(Constante.ZONE_SP))
				.valorUltimaContratacao(func.getValorFuncionalidade())
				.build();
	}
	
	
}
