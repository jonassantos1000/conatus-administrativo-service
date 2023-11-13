package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.entities.TenantEntity;
import br.com.app.conatus.commons.entities.TransacaoEntity;
import br.com.app.conatus.entities.factory.TransacaoEntityFactory;
import br.com.app.conatus.repositories.TransacaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransacaoService {
	
	private final TransacaoRepository transacaoRepository;
	
	protected TransacaoEntity gerarTransacao(TenantEntity tenant, PessoaFisicaEntity solicitante) {
		return transacaoRepository.save(TransacaoEntityFactory.converterParaEntity(solicitante, tenant));
	}
}
