package br.com.app.conatus.entities.factory;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.enums.TipoPessoaEnum;
import br.com.app.conatus.model.PessoaJuridicaRecord;

public class PessoaJuridicaEntityFactory {

	private PessoaJuridicaEntityFactory() {}
	
	public static PessoaJuridicaEntity converterRecordParaEntity(PessoaJuridicaRecord pj, DominioEntity ramoAtividade, DominioEntity situacao) {
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
