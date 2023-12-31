package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.DominioEntity;
import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.entities.PessoaJuridicaEntity;
import br.com.app.conatus.commons.entities.VinculoFuncionarioEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.commons.exceptions.MsgException;
import br.com.app.conatus.entities.factory.PessoaJuridicaEntityFactory;
import br.com.app.conatus.entities.factory.VinculoFuncionarioEntityFactory;
import br.com.app.conatus.model.request.PessoaJuridicaRecordRequest;
import br.com.app.conatus.repositories.PessoaJuridicaRepository;
import br.com.app.conatus.repositories.VinculoFuncionarioRepository;
import br.com.app.conatus.services.DominioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PessoaJuridicaService {

	private final DominioService dominioService;
	
	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	private final VinculoFuncionarioRepository vinculoFuncionarioRepository;
	
	protected PessoaJuridicaEntity salvarPessoaFisica(PessoaJuridicaRecordRequest dadosPessoa) {
		verificarPessoaJuridica(dadosPessoa.cnpj());
		
		return pessoaJuridicaRepository.save(PessoaJuridicaEntityFactory.converterRecordParaEntity(dadosPessoa,
				dominioService.recuperarPorId(dadosPessoa.idRamoAtividade()), dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO)));
	}
	
	protected VinculoFuncionarioEntity vincularFuncionarioEntity(PessoaFisicaEntity pf, PessoaJuridicaEntity pj, DominioEntity cargo) {
		return this.vinculoFuncionarioRepository.save(VinculoFuncionarioEntityFactory.converterParaEntity(pf, pj,
				dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO), cargo));
	}
	
	public void verificarPessoaJuridica(String cnpj) {
		if (pessoaJuridicaRepository.existsByCnpj(cnpj)) {
			throw new MsgException("Usuário informado já possui cadastro");
		}
	}
	
}
