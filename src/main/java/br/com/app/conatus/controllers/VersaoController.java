package br.com.app.conatus.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/versao")
public class VersaoController {
	
	@Value("${conatus.versao}") 
	private String versao;
	
	@GetMapping
	public Map<String, Object> consultarVersao() {
		
		Map<String, Object> versao = new HashMap<>();
		
		versao.put("versao", versao);
		
		return versao;
	}

}
