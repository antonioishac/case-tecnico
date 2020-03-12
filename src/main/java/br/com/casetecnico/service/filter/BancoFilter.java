package br.com.casetecnico.service.filter;

import lombok.Data;

/**
 * @author aishac
 * Filtros para realizar a busca de bancos.
 *
 */
@Data
public class BancoFilter {

	private String nome;
	private String agencia;
	private String cidade;
	private String cep;

}
