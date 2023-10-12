package br.com.app.conatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.app.conatus.repositories.JPAImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = JPAImpl.class)
public class ConatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConatusApplication.class, args);
	}

}
