package br.com.casetecnico.service.filter;

import lombok.Data;

/**
 * @author aishac
 * Classe que representa os campos de busca para realizar o filtro para os dados de estados.
 *
 */
@Data
public class EstadoFilter {

	private String nome;
	private String sigla;

}
