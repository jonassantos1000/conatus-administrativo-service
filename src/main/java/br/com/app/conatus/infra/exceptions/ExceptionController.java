package br.com.app.conatus.infra.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(MsgException.class)
	public ResponseEntity<ErroResponse> tratarErro400(MsgException e, HttpServletRequest request){
		ErroResponse erro = new ErroResponse(Instant.now(), new DetalheErroResponse("Falha na requisição", e.getMessage()), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
	
	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<ErroResponse> tratarErro404(NaoEncontradoException e, HttpServletRequest request){
		ErroResponse erro = new ErroResponse(Instant.now(), new DetalheErroResponse("Recurso não encontrado", e.getMessage()), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}
