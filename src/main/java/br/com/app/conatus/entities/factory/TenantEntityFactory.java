package br.com.app.conatus.entities.factory;

import java.util.UUID;

import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.TenantEntity;

public class TenantEntityFactory {
	private TenantEntityFactory() {}

	public static TenantEntity converterParaEntity(PessoaJuridicaEntity pj) {
		return TenantEntity.builder()
				.nome(pj.getNome())
				.pessoaJuridica(pj)
				.codigoTenant(UUID.randomUUID().toString())
				.build();
	}

}
