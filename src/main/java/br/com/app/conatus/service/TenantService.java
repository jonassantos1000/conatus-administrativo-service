package br.com.app.conatus.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.TenantEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.factory.TenantEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioTenantEntityFactory;
import br.com.app.conatus.entities.factory.VinculoFuncionarioEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.repositories.TenantRepository;
import br.com.app.conatus.repositories.UsuarioRepository;
import br.com.app.conatus.repositories.UsuarioTenantRepository;
import br.com.app.conatus.repositories.VinculoFuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {
	
	private final UsuarioRepository usuarioRepository;
	private final TenantRepository tenantRepository;
	private final UsuarioTenantRepository usuarioTenantRepository; 
	private final VinculoFuncionarioRepository vinculoFuncionarioRepository;
	
	private final DominioService dominioService;
	private final AutenticacaoService autenticacaoService;
	private final MovimentacaoService movimentacaoService; 
	private final PessoaFisicaService pessoaFisicaService;
	private final PessoaJuridicaService pessoaJuridicaService;
	
	@Transactional
	public void cadastrarTenant(SolicitacaoCadastroTenantRequest solicitacaoTenant) {
				
		DominioEntity situacaoAtiva = this.dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO);
		
		PessoaFisicaEntity pf = this.pessoaFisicaService.salvarPessoaFisica(solicitacaoTenant.usuario());

		PessoaJuridicaEntity pj = this.pessoaJuridicaService.salvarPessoaFisica(solicitacaoTenant.pessoaJuridica());
		
		UsuarioEntity solicitante = usuarioRepository
				.save(UsuarioEntityFactory.converterParaEntity(solicitacaoTenant.usuario(), pf, autenticacaoService.gerarHashSenha(solicitacaoTenant.usuario().senha()), situacaoAtiva));
		
		vinculoFuncionarioRepository.save(VinculoFuncionarioEntityFactory.converterParaEntity(pf, pj, situacaoAtiva, dominioService.recuperarPorCodigo(CodigoDominio.CARGO_PROPRIETARIO)));
		
		TenantEntity tenant = tenantRepository.save(TenantEntityFactory.converterParaEntity(pj));
		
		usuarioTenantRepository.save(UsuarioTenantEntityFactory.converterParaEntity(solicitante, tenant, situacaoAtiva));
		
		this.movimentacaoService.salvarMovimentacao(pf, BigDecimal.ZERO, tenant, CodigoDominio.MOVIM_ATIVACAO_AMOSTRA_GRATIS);
		
	}

}
