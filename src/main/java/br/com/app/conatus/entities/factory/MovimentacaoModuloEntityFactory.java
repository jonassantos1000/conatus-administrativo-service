package br.com.app.conatus.entities.factory;

import java.math.BigDecimal;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.TenantModuloFuncCustomEntity;
import br.com.app.conatus.entities.MovimentacaoModuloEntity;
import br.com.app.conatus.entities.TenantModuloEntity;
import br.com.app.conatus.entities.TransacaoEntity;

public class MovimentacaoModuloEntityFactory {

	private MovimentacaoModuloEntityFactory() {}

	public static MovimentacaoModuloEntity converterParaEntity(TenantModuloEntity moduloTenant,	TransacaoEntity transacao, BigDecimal valorBase, DominioEntity tipoMovim, TenantModuloFuncCustomEntity funcCustom) {
		
		return MovimentacaoModuloEntity.builder()
				.tenantModulo(moduloTenant)
				.transacao(transacao)
				.valorMovimentacao(valorBase)
				.tipo(tipoMovim)
				.funcionalidadeCustom(funcCustom)
				.build();
	}
}
