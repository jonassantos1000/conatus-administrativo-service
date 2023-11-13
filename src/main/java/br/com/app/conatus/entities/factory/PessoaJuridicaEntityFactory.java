package br.com.app.conatus.entities.factory;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.PessoaJuridicaEntity;
import br.com.app.conatus.commons.enums.TipoPessoaEnum;
import br.com.app.conatus.model.request.PessoaJuridicaRecordRequest;

public class PessoaJuridicaEntityFactory {

	private PessoaJuridicaEntityFactory() {}
	
	public static PessoaJuridicaEntity converterRecordParaEntity(PessoaJuridicaRecordRequest pj, DominioEntity ramoAtividade, DominioEntity situacao) {
		return PessoaJuridicaEntity.builder()
				.ramoAtividade(ramoAtividade)
				.situacao(situacao)
				.cnpj(pj.cnpj())
				.nome(pj.razaoSocial())
				.nomeFantasia(pj.nomeFantasia())
				.tipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA)
				.build();
	}
}
