package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.commons.exceptions.MsgException;
import br.com.app.conatus.commons.model.request.UsuarioRecordRequest;
import br.com.app.conatus.entities.factory.PessoaFisicaEntityFactory;
import br.com.app.conatus.repositories.PessoaFisicaRepository;
import br.com.app.conatus.services.DominioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaFisicaService {

	private final DominioService dominioService;
	
	private final PessoaFisicaRepository pessoaFisicaRepository;
	
	protected PessoaFisicaEntity salvarPessoaFisica(UsuarioRecordRequest dadosPessoa) {
		verificarPessoaFisica(dadosPessoa.cpf());
		
		return pessoaFisicaRepository.save(PessoaFisicaEntityFactory.converterRecordParaEntity(dadosPessoa,
				dominioService.recuperarPorId(dadosPessoa.idGenero()), dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO)));
	}
	
	public void verificarPessoaFisica(String cpf) {
		if (pessoaFisicaRepository.existsByCpf(cpf)) {
			throw new MsgException("Usuário informado já possui cadastro");
		}
	}
	
}
