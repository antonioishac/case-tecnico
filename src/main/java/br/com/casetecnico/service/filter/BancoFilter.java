package br.com.casetecnico.service.filter;

import lombok.Data;

@Data
public class BancoFilter {

	private String nome;
	private String agencia;
	private String cidade;
	private String cep;

}
