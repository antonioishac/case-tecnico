package br.com.casetecnico.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("case-tecnico")
public class CaseTecnicoApiProperty {

	private String originPermitida = "http://localhost:4200";

	public String getOriginPermitida() {
		return originPermitida;
	}

	public void setOriginPermitida(String originPermitida) {
		this.originPermitida = originPermitida;
	}

}