package br.com.casetecnico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import br.com.casetecnico.config.property.CaseTecnicoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CaseTecnicoApiProperty.class)
public class CaseTecnicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseTecnicoApplication.class, args);
	}

}
