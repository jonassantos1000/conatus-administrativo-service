package br.com.app.conatus;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.app.conatus.repositories.JPAImpl;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories(repositoryBaseClass = JPAImpl.class)
public class ConatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConatusApplication.class, args);
	}
	
    @PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
    }
}
