package br.com.app.conatus.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.TenantEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.UsuarioTenantEntity;
import br.com.app.conatus.entities.factory.TenantEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioTenantEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.repositories.TenantRepository;
import br.com.app.conatus.repositories.UsuarioTenantRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {

	private final TenantRepository tenantRepository;
	private final UsuarioTenantRepository usuarioTenantRepository; 

	private final DominioService dominioService;
	private final MovimentacaoService movimentacaoService; 
	private final PessoaFisicaService pessoaFisicaService;
	private final PessoaJuridicaService pessoaJuridicaService;
	private final UsuarioService usuarioService;
	
	@Transactional
	public void cadastrarTenant(SolicitacaoCadastroTenantRequest solicitacaoTenant) {

		PessoaFisicaEntity pf = this.pessoaFisicaService.salvarPessoaFisica(solicitacaoTenant.usuario());

		PessoaJuridicaEntity pj = this.pessoaJuridicaService.salvarPessoaFisica(solicitacaoTenant.pessoaJuridica());

		UsuarioEntity solicitante = this.usuarioService.salvarUsuario(solicitacaoTenant.usuario(), pf);

		this.pessoaJuridicaService.vincularFuncionarioEntity(pf, pj, dominioService.recuperarPorCodigo(CodigoDominio.CARGO_PROPRIETARIO));

		TenantEntity tenant = tenantRepository.save(TenantEntityFactory.converterParaEntity(pj));

		vincularUsuarioTenant(solicitante, tenant);

		this.movimentacaoService.salvarMovimentacao(pf, BigDecimal.ZERO, tenant,
				CodigoDominio.MOVIM_ATIVACAO_AMOSTRA_GRATIS);
	}
	
	public UsuarioTenantEntity vincularUsuarioTenant(UsuarioEntity usuario, TenantEntity tenant) {
		return usuarioTenantRepository.save(UsuarioTenantEntityFactory.converterParaEntity(usuario, tenant,
				this.dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO)));
	}

}
