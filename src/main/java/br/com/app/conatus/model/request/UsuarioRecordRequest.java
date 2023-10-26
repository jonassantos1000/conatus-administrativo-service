package br.com.app.conatus.model.request;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioRecordRequest(

		@CPF
		String cpf, 
		
		@NotNull(message = "O campo genero é obrigatorio")
		Long idGenero,
		
		@NotBlank(message = "O campo nome é obrigatório")
		String nome, 
		
		@Email
		String email,
		
		@NotBlank(message = "O campo telefone é obrigatorio")
		String telefone,
		
		@NotBlank(message = "O campo senha é obrigatorio")
		String senha) {

}
