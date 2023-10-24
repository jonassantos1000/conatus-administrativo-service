package br.com.app.conatus.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import br.com.app.conatus.enums.CodigoDominio;
import br.com.app.conatus.model.response.DominioResponse;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class DominioControllerTest extends AbstractControllerTest{
	
	private static StringBuilder path = new StringBuilder("/dominios");
	
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
		
		path.append(1L);

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), DominioResponse.class);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}
	
	@Test
	@Order(3)
	void esperaBuscarDominioPorCodigoTipo() {
		
		path.append(1L);

		var respostaRequisicao = restTemplate.exchange(path.toString(), HttpMethod.GET,
				new HttpEntity<>(getHeader()), List<DominioResponse>);

		assertEquals(HttpStatus.OK, respostaRequisicao.getStatusCode());
		
	}

}
