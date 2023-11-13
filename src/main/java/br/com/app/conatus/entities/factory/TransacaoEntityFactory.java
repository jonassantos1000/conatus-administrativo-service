package br.com.app.conatus.entities.factory;

import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.entities.TenantEntity;
import br.com.app.conatus.commons.entities.TransacaoEntity;

public class TransacaoEntityFactory {

	public static TransacaoEntity converterParaEntity(PessoaFisicaEntity pf, TenantEntity tenant) {
		return TransacaoEntity.builder()
				.pessoaFisica(pf)
				.tenant(tenant)
				.build();
	}

}
