package br.com.app.conatus.entities.factory;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.enums.TipoPessoaEnum;
import br.com.app.conatus.model.UsuarioRecord;

public class PessoaFisicaEntityFactory {

	private PessoaFisicaEntityFactory () {}
	
	public static PessoaFisicaEntity converterRecordParaEntity(UsuarioRecord usuario, DominioEntity genero, DominioEntity situacao) {
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
