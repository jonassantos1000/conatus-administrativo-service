package br.com.app.conatus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import br.com.app.conatus.model.PessoaJuridicaRecord;
import br.com.app.conatus.model.UsuarioRecord;
import br.com.app.conatus.model.request.ModuloRequest;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class TenantControllerTest extends AbstractControllerTest{
	
	private static StringBuilder path = new StringBuilder("/tenant");
	
	@Test
	@Order(1)
	void esperaCadastrarTenant() {
		
		SolicitacaoCadastroTenantRequest solicitacaoTenant = gerarSolicitacaoTenant();

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(solicitacaoTenant, getHeader()), solicitacaoTenant.getClass());

		assertEquals(HttpStatus.CREATED, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(2)
	void esperaNaoCadastrarTenantDeEmpresaJaCadastrada() {
		
		SolicitacaoCadastroTenantRequest solicitacaoTenant = gerarSolicitacaoTenant();

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(solicitacaoTenant, getHeader()), solicitacaoTenant.getClass());

		System.out.println(respostaRequisicao.getBody());
		
		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
		
	}

	private SolicitacaoCadastroTenantRequest gerarSolicitacaoTenant() {
		return new SolicitacaoCadastroTenantRequest(gerarPessoaJuridica(), gerarUsuario(), List.of(gerarModulo()));
	}
	
	private PessoaJuridicaRecord gerarPessoaJuridica() {
		
		return new PessoaJuridicaRecord("TESTE NOME FANTASIA", "21240122000125", "razaoSocial JUNIT", 400L);
	}
	
	private UsuarioRecord gerarUsuario() {
		
		return new UsuarioRecord("97092797004", 700L, "jonas silva", "teste@junit.com.br", "123456789", "123456");
	}
	
	private ModuloRequest gerarModulo() {
		return new ModuloRequest(1L, List.of());
	}

}