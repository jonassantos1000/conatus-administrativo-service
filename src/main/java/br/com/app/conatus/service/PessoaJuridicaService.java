package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.factory.PessoaJuridicaEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.infra.exceptions.MsgException;
import br.com.app.conatus.model.request.PessoaJuridicaRecordRequest;
import br.com.app.conatus.repositories.PessoaJuridicaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaService {

	private final DominioService dominioService;
	
	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	
	protected PessoaJuridicaEntity salvarPessoaFisica(PessoaJuridicaRecordRequest dadosPessoa) {
		verificarPessoaJuridica(dadosPessoa.cnpj());
		
		return pessoaJuridicaRepository.save(PessoaJuridicaEntityFactory.converterRecordParaEntity(dadosPessoa,
				dominioService.recuperarPorId(dadosPessoa.idRamoAtividade()), dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO)));
	}
	
	protected void verificarPessoaJuridica(String cnpj) {
		if (pessoaJuridicaRepository.existsByCnpj(cnpj)) {
			throw new MsgException("Usuário informado já possui cadastro");
		}
	}
	
}
