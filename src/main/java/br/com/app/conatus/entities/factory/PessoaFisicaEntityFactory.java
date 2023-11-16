package br.com.app.conatus.entities.factory;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.enums.TipoPessoaEnum;
import br.com.app.conatus.model.request.UsuarioRecordRequest;

public class PessoaFisicaEntityFactory {

	private PessoaFisicaEntityFactory () {}
	
	public static PessoaFisicaEntity converterRecordParaEntity(UsuarioRecordRequest usuario, DominioEntity genero, DominioEntity situacao) {
		return PessoaFisicaEntity.builder()
				.nome(usuario.nome())
				.email(usuario.email())
				.telefone(usuario.telefone())
				.tipoPessoa(TipoPessoaEnum.PESSOA_JURIDICA)
				.genero(genero)
				.cpf(usuario.cpf())
				.situacao(situacao)
				.build();
	}
}
