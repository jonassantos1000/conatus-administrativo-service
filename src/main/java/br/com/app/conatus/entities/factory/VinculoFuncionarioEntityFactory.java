package br.com.app.conatus.entities.factory;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.entities.PessoaJuridicaEntity;
import br.com.app.conatus.commons.entities.VinculoFuncionarioEntity;

public class VinculoFuncionarioEntityFactory {
	
	private VinculoFuncionarioEntityFactory() {}

	public static VinculoFuncionarioEntity converterParaEntity(PessoaFisicaEntity pf, PessoaJuridicaEntity pj, DominioEntity situacaoAtiva, DominioEntity cargoSolicitante) {
		return VinculoFuncionarioEntity.builder()
				.empresa(pj)
				.funcionario(pf)
				.situacao(situacaoAtiva)
				.cargo(cargoSolicitante)
				.build();
	}
	
}
