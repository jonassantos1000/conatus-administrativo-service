package br.com.app.conatus;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class ConatusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConatusApplication.class, args);
	}
	
    @PostConstruct
    public void init(){
      TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
    }
}
