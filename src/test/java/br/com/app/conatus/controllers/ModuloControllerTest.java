package br.com.app.conatus.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

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

import br.com.app.conatus.model.response.ModuloRecordResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
class ModuloControllerTest extends AbstractControllerTest{
	
	private static final String RESOURCE = "/modulos";
	private static StringBuilder path = new StringBuilder();
	
	@Test
	@Order(1)
	void esperaBuscarModulos() {
		
		ParameterizedTypeReference<List<ModuloRecordResponse>> responseType = new ParameterizedTypeReference<>() {
		};

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), responseType);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		assertFalse(respostaRequisicao.getBody().isEmpty());
	}
	
	@Test
	@Order(2)
	void esperaBuscarModuloPorId() {
		
		path.append("/").append(1L);

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), ModuloRecordResponse.class);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(3)
	void esperaNaoEncontrarModuloPorIdInexistente() {
		
		path.append("/").append(0L);
		
		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), ModuloRecordResponse.class);

		assertEquals(HttpStatus.NOT_FOUND, respostaRequisicao.getStatusCode());
		
	}
	
	@BeforeEach
	void inicializar() {
		path.setLength(0);
		path.append(RESOURCE);
	}

}
