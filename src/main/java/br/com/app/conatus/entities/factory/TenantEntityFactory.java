package br.com.app.conatus.entities.factory;

import java.time.ZonedDateTime;
import java.util.UUID;

import br.com.app.conatus.commons.constantes.Constante;
import br.com.app.conatus.commons.entities.PessoaJuridicaEntity;
import br.com.app.conatus.commons.entities.TenantEntity;

public class TenantEntityFactory {
	private TenantEntityFactory() {}

	public static TenantEntity converterParaEntity(PessoaJuridicaEntity pj) {
		return TenantEntity.builder()
				.nome(pj.getNome())
				.pessoaJuridica(pj)
				.quantidadeUsuarios(1)
				.dataInicioAmostraGratis(ZonedDateTime.now(Constante.ZONE_SP))
				.dataFimAmostraGratis(ZonedDateTime.now(Constante.ZONE_SP).plusDays(7))
				.codigoTenant(UUID.randomUUID().toString())
				.build();
	}

}
