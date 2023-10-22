package br.com.app.conatus.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.FuncionalidadeCustomizadaEntity;
import br.com.app.conatus.entities.FuncionalidadeEntity;
import br.com.app.conatus.entities.ModuloEntity;
import br.com.app.conatus.entities.MovimentacaoModuloEntity;
import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.TenantEntity;
import br.com.app.conatus.entities.TenantModuloEntity;
import br.com.app.conatus.entities.TransacaoEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.factory.FuncionalidadeCustomizadaEntityFactory;
import br.com.app.conatus.entities.factory.MovimentacaoModuloEntityFactory;
import br.com.app.conatus.entities.factory.PessoaFisicaEntityFactory;
import br.com.app.conatus.entities.factory.PessoaJuridicaEntityFactory;
import br.com.app.conatus.entities.factory.TenantEntityFactory;
import br.com.app.conatus.entities.factory.TenantModuloEntityFactory;
import br.com.app.conatus.entities.factory.TransacaoEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioTenantEntityFactory;
import br.com.app.conatus.entities.factory.VinculoFuncionarioEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.infra.exceptions.MsgException;
import br.com.app.conatus.infra.exceptions.NaoEncontradoException;
import br.com.app.conatus.model.PessoaJuridicaRecord;
import br.com.app.conatus.model.UsuarioRecord;
import br.com.app.conatus.model.request.FuncionalidadeRequest;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.repositories.FuncionalidadeCustomizadaRepository;
import br.com.app.conatus.repositories.FuncionalidadeRepository;
import br.com.app.conatus.repositories.ModuloRepository;
import br.com.app.conatus.repositories.MovimentacaoModuloRepository;
import br.com.app.conatus.repositories.PessoaFisicaRepository;
import br.com.app.conatus.repositories.PessoaJuridicaRepository;
import br.com.app.conatus.repositories.TenantModuloRepository;
import br.com.app.conatus.repositories.TenantRepository;
import br.com.app.conatus.repositories.TransacaoRepository;
import br.com.app.conatus.repositories.UsuarioRepository;
import br.com.app.conatus.repositories.UsuarioTenantRepository;
import br.com.app.conatus.repositories.VinculoFuncionarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {
	
	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	private final UsuarioRepository usuarioRepository;
	private final TenantRepository tenantRepository;
	private final UsuarioTenantRepository usuarioTenantRepository; 
	private final VinculoFuncionarioRepository vinculoFuncionarioRepository;
	private final ModuloRepository moduloRepository;
	private final TransacaoRepository transacaoRepository;
	private final FuncionalidadeRepository funcionalidadeRepository;
	private final FuncionalidadeCustomizadaRepository funcionalidadeCustomizadaRepository;
	private final MovimentacaoModuloRepository movimentacaoModuloRepository;
	private final TenantModuloRepository tenantModuloRepository;
	
	private final DominioService dominioService;
	private final AutenticacaoService autenticacaoService;
	
	@Transactional
	public void cadastrarTenant(SolicitacaoCadastroTenantRequest solicitacaoTenant) {
		
		validarSolicitacaoCadastroTenant(solicitacaoTenant);
		
		DominioEntity situacaoAtiva = dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO);
		
		PessoaFisicaEntity pf = pessoaFisicaRepository.save(PessoaFisicaEntityFactory.converterRecordParaEntity(solicitacaoTenant.usuario(),
				dominioService.recuperarPorId(solicitacaoTenant.usuario().idGenero()), situacaoAtiva));

		UsuarioEntity solicitante = usuarioRepository
				.save(UsuarioEntityFactory.converterParaEntity(solicitacaoTenant.usuario(), pf, autenticacaoService.gerarHashSenha(solicitacaoTenant.usuario().senha()), situacaoAtiva));

		PessoaJuridicaEntity pj = pessoaJuridicaRepository.save(PessoaJuridicaEntityFactory.converterRecordParaEntity(solicitacaoTenant.pessoaJuridica(), 
				dominioService.recuperarPorId(solicitacaoTenant.pessoaJuridica().idRamoAtividade()), situacaoAtiva));
		
		vinculoFuncionarioRepository.save(VinculoFuncionarioEntityFactory.converterParaEntity(pf, pj, situacaoAtiva, dominioService.recuperarPorCodigo(CodigoDominio.CARGO_PROPRIETARIO)));
		
		TenantEntity tenant = tenantRepository.save(TenantEntityFactory.converterParaEntity(pj));
		
		usuarioTenantRepository.save(UsuarioTenantEntityFactory.converterParaEntity(solicitante, tenant, situacaoAtiva));
		
		TransacaoEntity transacao = transacaoRepository.save(TransacaoEntityFactory.converterParaEntity(pf, tenant));
				
		solicitacaoTenant.modulos().forEach(dadosModulo -> {
			
			ModuloEntity modulo = recuperarModuloPorId(dadosModulo.idModulo());
			
			boolean isPossuiFuncionalidadePremium = ! CollectionUtils.isEmpty(dadosModulo.funcPremium());
			
			TenantModuloEntity moduloTenant = tenantModuloRepository.save(TenantModuloEntityFactory.converterParaEntity(modulo, tenant, situacaoAtiva, LocalDateTime.now().plusDays(7L), isPossuiFuncionalidadePremium));
			
			salvarMovimentacao(moduloTenant, transacao, modulo.getValorBase(),
							dominioService.recuperarPorCodigo(CodigoDominio.MOVIM_CONTRATACAO_MODULO));	

			if (isPossuiFuncionalidadePremium) {
				salvarFuncionalidadePremium(dadosModulo.funcPremium(), moduloTenant, transacao);
			}
			
		});

	}
	
	private void salvarFuncionalidadePremium(List<FuncionalidadeRequest> funcionalidadesPremium, TenantModuloEntity tenantModulo, TransacaoEntity transacao) {
		
		funcionalidadesPremium.stream().forEach(funcRequest -> {
			
			FuncionalidadeEntity func = recuperarFuncionalidadePorId(funcRequest.idFuncionalidade());
			
			if (!func.getModulo().getCodigo().equals(tenantModulo.getModulo().getTipoModulo().getCodigo())) {
				throw new MsgException(String.format("Funcionalidade informada para o modulo %s é invalida", tenantModulo.getModulo().getTipoModulo().getDescricao()));
			}
			
			if (func.getTipoFuncionalidade().getCodigo().equals(CodigoDominio.FUNC_PADRAO.name())) {
				throw new MsgException(String.format("Funcionalidade %d informada para o modulo %s não é considerada premium", funcRequest.idFuncionalidade(), tenantModulo.getModulo().getTipoModulo().getDescricao()));
			}
			
			FuncionalidadeCustomizadaEntity funcCustom = funcionalidadeCustomizadaRepository.save(FuncionalidadeCustomizadaEntityFactory
					.converterParaEntity(tenantModulo, func, 
							dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO),
							LocalDateTime.now().plusDays(7L)));
			
			salvarMovimentacao(tenantModulo, transacao, funcCustom.getValorUltimaContratacao(), 
					dominioService.recuperarPorCodigo(CodigoDominio.MOVIM_ATIVACAO_FUNC_PREMIUM), funcCustom);
		});
	}
	
	private MovimentacaoModuloEntity salvarMovimentacao(TenantModuloEntity moduloTenant, TransacaoEntity transacao, BigDecimal valorMovim, DominioEntity tipoMovim, FuncionalidadeCustomizadaEntity funcCustom) {
		totalizarTransacao(transacao, valorMovim);
		
		return movimentacaoModuloRepository.save(MovimentacaoModuloEntityFactory
				.converterParaEntity(moduloTenant, transacao, valorMovim, tipoMovim, funcCustom));
	}
	
	private void totalizarTransacao(TransacaoEntity transacao, BigDecimal valorMovim) {
		transacao.getValorTransacao().add(valorMovim);
	}

	private MovimentacaoModuloEntity salvarMovimentacao(TenantModuloEntity moduloTenant, TransacaoEntity transacao, BigDecimal valorBase, DominioEntity tipoMovim) {
		return salvarMovimentacao(moduloTenant, transacao, valorBase, tipoMovim, null);
	}

	private ModuloEntity recuperarModuloPorId(Long idModulo) {
		return moduloRepository.findById(idModulo)
				.orElseThrow(() -> new NaoEncontradoException(String.format("Não foi encontrado um modulo com id: %d", idModulo)));
	}
	
	private FuncionalidadeEntity recuperarFuncionalidadePorId(Long idFunc) {
		return funcionalidadeRepository.findById(idFunc)
				.orElseThrow(() -> new NaoEncontradoException(String.format("Não foi encontrado uma funcionalidade com id: %d", idFunc)));
	}

	private void validarSolicitacaoCadastroTenant(SolicitacaoCadastroTenantRequest solicitacaoTenant) {
		verificarPessoaJuridica(solicitacaoTenant.pessoaJuridica());
		verificarPessoaFisica(solicitacaoTenant.usuario());
	}

	private void verificarPessoaFisica(UsuarioRecord usuario) {
		if (pessoaFisicaRepository.existsByCpf(usuario.cpf())) {
			throw new MsgException("Usuário informado já possui cadastro");
		}
	}

	private void verificarPessoaJuridica(PessoaJuridicaRecord pessoaJuridica) {
		if (pessoaJuridicaRepository.existsByCnpj(pessoaJuridica.cnpj())) {
			throw new MsgException("Pessoa juridica informada já possui cadastro");
		}
	}

}
