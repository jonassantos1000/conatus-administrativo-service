package br.com.app.conatus.service;

import org.springframework.stereotype.Service;

import br.com.app.conatus.entities.DominioEntity;
import br.com.app.conatus.entities.PessoaFisicaEntity;
import br.com.app.conatus.entities.PessoaJuridicaEntity;
import br.com.app.conatus.entities.UsuarioEntity;
import br.com.app.conatus.entities.factory.PessoaFisicaEntityFactory;
import br.com.app.conatus.entities.factory.PessoaJuridicaEntityFactory;
import br.com.app.conatus.entities.factory.UsuarioEntityFactory;
import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.model.PessoaJuridicaRecord;
import br.com.app.conatus.model.UsuarioRecord;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.repositories.PessoaFisicaRepository;
import br.com.app.conatus.repositories.PessoaJuridicaRepository;
import br.com.app.conatus.repositories.TenantRepository;
import br.com.app.conatus.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TenantService {
	
	private final PessoaFisicaRepository pessoaFisicaRepository;
	private final PessoaJuridicaRepository pessoaJuridicaRepository;
	private final UsuarioRepository usuarioRepository;
	private final TenantRepository tenantRepository;
	
	private final DominioService dominioService;
	
	@Transactional
	public void cadastrarTenant(SolicitacaoCadastroTenantRequest solicitacaoTenant) {
		
		validarSolicitacaoCadastroTenant(solicitacaoTenant);
		
		DominioEntity situacaoAtiva = dominioService.recuperarPorCodigo(CodigoDominio.STATUS_ATIVO);
		
		PessoaFisicaEntity pf = pessoaFisicaRepository.save(PessoaFisicaEntityFactory.converterRecordParaEntity(solicitacaoTenant.usuario(),
				dominioService.recuperarPorId(solicitacaoTenant.usuario().idGenero()), situacaoAtiva));

		UsuarioEntity save = usuarioRepository
				.save(UsuarioEntityFactory.converterParaEntity(solicitacaoTenant.usuario(), pf, situacaoAtiva));

		PessoaJuridicaEntity pj = pessoaJuridicaRepository.save(PessoaJuridicaEntityFactory.converterRecordParaEntity(
				solicitacaoTenant.pessoaJuridica(),
				dominioService.recuperarPorId(solicitacaoTenant.pessoaJuridica().idRamoAtividade()), situacaoAtiva));
		
		

	}

	private void validarSolicitacaoCadastroTenant(SolicitacaoCadastroTenantRequest solicitacaoTenant) {
		verificarPessoaJuridica(solicitacaoTenant.pessoaJuridica());
		verificarPessoaFisica(solicitacaoTenant.usuario());
	}

	private void verificarPessoaFisica(UsuarioRecord usuario) {
		if (pessoaFisicaRepository.existsByCpf(usuario.cpf())) {
			throw new IllegalArgumentException("Usuário informado já possui cadastro");
		}
	}

	private void verificarPessoaJuridica(PessoaJuridicaRecord pessoaJuridica) {
		if (pessoaJuridicaRepository.existsByCnpj(pessoaJuridica.cnpj())) {
			throw new IllegalArgumentException("Pessoa juridica informada já possui cadastro");
		}
	}

}
