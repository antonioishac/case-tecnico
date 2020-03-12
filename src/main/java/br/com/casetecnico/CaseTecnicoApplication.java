package br.com.casetecnico;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import br.com.casetecnico.config.property.CaseTecnicoApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(CaseTecnicoApiProperty.class)
public class CaseTecnicoApplication implements ClientHttpRequestInterceptor {

	private static final String HEADER_VALUE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36";

	public static void main(String[] args) {
		SpringApplication.run(CaseTecnicoApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.interceptors(this).build();
	}

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		final HttpHeaders headers = request.getHeaders();
		headers.set("User-Agent", HEADER_VALUE);
		return execution.execute(request, body);
	}

}
