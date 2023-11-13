package br.com.app.conatus.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.app.conatus.commons.entities.MovimentacaoEntity;
import br.com.app.conatus.commons.entities.PessoaFisicaEntity;
import br.com.app.conatus.commons.entities.TenantEntity;
import br.com.app.conatus.commons.entities.TransacaoEntity;
import br.com.app.conatus.commons.enums.CodigoDominio;
import br.com.app.conatus.entities.factory.MovimentacaoEntityFactory;
import br.com.app.conatus.repositories.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
	
	private final MovimentacaoRepository movimentacaoRepository;
	
	private final TransacaoService transacaoService;
	
	private final DominioService dominioService;
	
	public MovimentacaoEntity salvarMovimentacao(PessoaFisicaEntity solicitante, BigDecimal valorMovimentacao, TenantEntity tenant, CodigoDominio codigoMovimentacao) {
		
		TransacaoEntity transacao = this.transacaoService.gerarTransacao(tenant, solicitante);
		
		return movimentacaoRepository.save(MovimentacaoEntityFactory.converterParaEntity(transacao, valorMovimentacao, dominioService.recuperarPorCodigo(codigoMovimentacao.name())));
	}
}
