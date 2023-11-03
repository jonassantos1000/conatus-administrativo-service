package br.com.app.conatus.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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

import br.com.app.conatus.model.request.PessoaJuridicaRecordRequest;
import br.com.app.conatus.model.request.SolicitacaoCadastroTenantRequest;
import br.com.app.conatus.model.request.UsuarioRecordRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class TenantControllerTest extends AbstractControllerTest{
	
	private static final String RESOURCE = "/tenant";
	private static StringBuilder path = new StringBuilder(RESOURCE);
	
	@Test
	@Order(1)
	void esperaCadastrarTenant() {
		
		SolicitacaoCadastroTenantRequest solicitacaoTenant = gerarSolicitacaoTenant();

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(solicitacaoTenant, getHeader()), Void.class);
		
		System.out.println(respostaRequisicao.getBody());

		assertEquals(HttpStatus.CREATED, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(2)
	void esperaNaoCadastrarTenantDeEmpresaJaCadastrada() {
		
		SolicitacaoCadastroTenantRequest solicitacaoTenant = gerarSolicitacaoTenant();

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(solicitacaoTenant, getHeader()), solicitacaoTenant.getClass());
		
		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
		
	}

	private SolicitacaoCadastroTenantRequest gerarSolicitacaoTenant() {
		return new SolicitacaoCadastroTenantRequest(gerarPessoaJuridica(), gerarUsuario());
	}
	
	private PessoaJuridicaRecordRequest gerarPessoaJuridica() {
		
		return new PessoaJuridicaRecordRequest("TESTE NOME FANTASIA", "razaoSocial JUNIT", "37039861000193", 4L);
	}
	
	private UsuarioRecordRequest gerarUsuario() {
		
		return new UsuarioRecordRequest("51494893010", 1L, "jonas silva", "teste@junit.com.br", "123456789", "123456");
	}
	
	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}

}
