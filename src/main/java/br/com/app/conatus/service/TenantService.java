package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.LicencaEntity;
import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.TenantEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.factory.PessoaFisicaEntityFactory;
import br.com.app.conatus.entities.factory.PessoaJuridicaEntityFactory;
import br.com.app.conatus.entities.factory.PlanoContratacaoEntityFactory;
import br.com.app.conatus.entities.factory.TenantEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioTenantEntityFactory;
import br.com.app.conatus.entities.factory.VinculoFuncionarioEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.infra.exceptions.MsgException;
import br.com.app.conatus.infra.exceptions.NaoEncontradoException;
import br.com.app.conatus.model.PessoaJuridicaRecord;
import br.com.app.conatus.model.UsuarioRecord;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.repositories.LicencaRepository;
import br.com.app.conatus.repositories.PessoaFisicaRepository;
import br.com.app.conatus.repositories.PessoaJuridicaRepository;
import br.com.app.conatus.repositories.PlanoContratacaoRepository;
import br.com.app.conatus.repositories.TenantRepository;
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
	private final LicencaRepository licencaRepository;
	private final PlanoContratacaoRepository planoContratacaoRepository;
	private final UsuarioTenantRepository usuarioTenantRepository; 
	private final VinculoFuncionarioRepository vinculoFuncionarioRepository;
	
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
		
		planoContratacaoRepository.save(PlanoContratacaoEntityFactory.converterParaEntity(tenant, situacaoAtiva, 
				recuperarLicencaPorId(solicitacaoTenant.idPlano())));

	}
	
	private LicencaEntity recuperarLicencaPorId(Long id) {
		return licencaRepository.findById(id)
				.orElseThrow(() -> new NaoEncontradoException("Licença informada é invalida"));
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
