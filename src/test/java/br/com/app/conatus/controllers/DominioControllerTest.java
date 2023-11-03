package br.com.app.conatus.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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

import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.model.response.DominioResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class DominioControllerTest extends AbstractControllerTest{
	
	private static final String RESOURCE = "/dominios";
	private static StringBuilder path = new StringBuilder();
	
	@Test
	@Order(1)
	void esperaBuscarDominioPorCodigo() {
		
		path.append("/codigos/").append(CodigoDominio.STATUS_ATIVO);

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), DominioResponse.class);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(2)
	void esperaBuscarDominioPorId() {
		
		path.append("/").append(1L);

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), DominioResponse.class);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(3)
	void esperaBuscarDominioPorIdTipo() {
		
		path.append("/tipos-id/").append(1L);
		
		ParameterizedTypeReference<List<DominioResponse>> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(4)
	void esperaBuscarDominioPorCodigoTipo() {
		
		path.append("/tipos-codigos/").append("CARGOS");
		
		ParameterizedTypeReference<List<DominioResponse>> responseType = new ParameterizedTypeReference<List<DominioResponse>>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}
	
	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}

}
