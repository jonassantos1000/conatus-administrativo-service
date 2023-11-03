package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.factory.UsuarioEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.model.request.UsuarioRecordRequest;
import br.com.app.conatus.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {
	
	private final AutenticacaoService autenticacaoService;
	private final DominioService dominioService;
	private final UsuarioRepository usuarioRepository;

	public UsuarioEntity salvarUsuario(UsuarioRecordRequest dadosUsuario, PessoaFisicaEntity pf) {
		
		return usuarioRepository.save(UsuarioEntityFactory.converterParaEntity(dadosUsuario, pf,
				autenticacaoService.gerarHashSenha(dadosUsuario.senha()),
				dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO)));
	}
	
}
