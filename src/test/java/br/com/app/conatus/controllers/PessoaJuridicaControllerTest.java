package br.com.app.conatus.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import br.com.app.conatus.infra.exceptions.ErroResponse;
import br.com.app.conatus.model.request.PessoaJuridicaValidacaoRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class PessoaJuridicaControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/pessoas-juridicas";
	private static StringBuilder path = new StringBuilder();
	
	@Test
	@Order(1)
	void esperaQueSejaRetornadoSucessoNaValidacaoCnpj() {
		
		path.append("/validacao-cnpj");
		
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarCnpjRequestValido(), getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(2)
	void esperaRetornarErroNaVerificaoComCnpjInvalido() {
		
		path.append("/validacao-cnpj");
		
		ParameterizedTypeReference<ErroResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarCnpjRequestInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
		assertFalse(respostaRequisicao.getBody().erros().isEmpty());
	}
	
	@Test
	@Order(3)
	void esperaRetornarErroNaVerificaoComCnpjVazio() {
		
		path.append("/validacao-cnpj");
		
		ParameterizedTypeReference<ErroResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarCnpjRequestVazio(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
		assertFalse(respostaRequisicao.getBody().erros().isEmpty());
	}

	private PessoaJuridicaValidacaoRequest gerarCnpjRequestValido() {
		return PessoaJuridicaValidacaoRequest.builder().cnpj("74603057000111").build();
	}
	
	private PessoaJuridicaValidacaoRequest gerarCnpjRequestInvalido() {
		return PessoaJuridicaValidacaoRequest.builder().cnpj("00000000000").build();
	}
	
	private PessoaJuridicaValidacaoRequest gerarCnpjRequestVazio() {
		return PessoaJuridicaValidacaoRequest.builder().cnpj(StringUtils.EMPTY).build();
	}
	
	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}

}
