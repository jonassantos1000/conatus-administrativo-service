package br.com.app.conatus.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;

public abstract class AbstractControllerTest {

	@Autowired
	TestRestTemplate restTemplate;
	
	protected HttpHeaders getHeader() {
		HttpHeaders header = new HttpHeaders();
		header.setBearerAuth(UUID.randomUUID().toString());
	    return header;
	}
}
