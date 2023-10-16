package br.com.app.conatus.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService {
	
	public String gerarHashSenha(String senha) {
        String salt = BCrypt.gensalt(12);
        return BCrypt.hashpw(senha, salt);
	}
	
	public boolean isSenhaValida(String senha, String hash) {
        return BCrypt.checkpw(senha, hash);
	}

}
