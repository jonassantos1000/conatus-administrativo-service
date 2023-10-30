package br.com.app.conatus.entities.factory;

import java.time.LocalDateTime;

import br.com.app.conatus.constantes.Constante;
import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.TenantModuloFuncCustomEntity;
import br.com.app.conatus.entities.FuncionalidadeEntity;
import br.com.app.conatus.entities.TenantModuloEntity;

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
