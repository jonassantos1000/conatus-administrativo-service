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
import br.com.app.conatus.model.request.PessoaFisicaValidacaoRequest;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class PessoaFisicaControllerTest extends AbstractControllerTest {

	private static final String RESOURCE = "/pessoas-fisicas";
	private static StringBuilder path = new StringBuilder();
	
	@Test
	@Order(1)
	void esperaQueSejaRetornadoSucessoNaValidacaoCpf() {
		
		path.append("/validacao-cpf");
		
		ParameterizedTypeReference<Void> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarCpfRequestValido(), getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
	}
	
	@Test
	@Order(2)
	void esperaRetornarErroNaVerificaoComCpfInvalido() {
		
		path.append("/validacao-cpf");
		
		ParameterizedTypeReference<ErroResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarCpfRequestInvalido(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
		assertFalse(respostaRequisicao.getBody().erros().isEmpty());
	}
	
	@Test
	@Order(3)
	void esperaRetornarErroNaVerificaoComCnpjVazio() {
		
		path.append("/validacao-cpf");
		
		ParameterizedTypeReference<ErroResponse> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.POST,
				new HttpEntity<>(gerarCpfRequestVazio(), getHeader()), responseType);

		assertEquals(HttpStatus.BAD_REQUEST, respostaRequisicao.getStatusCode());
		assertFalse(respostaRequisicao.getBody().erros().isEmpty());
	}

	private PessoaFisicaValidacaoRequest gerarCpfRequestValido() {
		return PessoaFisicaValidacaoRequest.builder().cpf("87579012014").build();
	}
	
	private PessoaFisicaValidacaoRequest gerarCpfRequestInvalido() {
		return PessoaFisicaValidacaoRequest.builder().cpf("00000000000").build();
	}
	
	private PessoaFisicaValidacaoRequest gerarCpfRequestVazio() {
		return PessoaFisicaValidacaoRequest.builder().cpf(StringUtils.EMPTY).build();
	}
	
	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}

}
