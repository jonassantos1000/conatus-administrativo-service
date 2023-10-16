package br.com.app.conatus.entities.factory;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.model.UsuarioRecord;

public class UsuarioEntityFactory {

	private UsuarioEntityFactory() {}

	public static UsuarioEntity converterParaEntity(UsuarioRecord usuario, PessoaFisicaEntity pf, String hashSenha,DominioEntity situacaoAtiva) {
		
		return UsuarioEntity.builder()
				.pessoa(pf)
				.situacao(situacaoAtiva)
				.senha(hashSenha)
				.build();
	}
	
}
