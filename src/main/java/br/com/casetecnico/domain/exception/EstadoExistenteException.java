package br.com.casetecnico.domain.exception;

/**
 * @author aishac
 * Classe de exception que trata se o estado j\u00e1 est\u00e1 cadastrado.
 *
 */
public class EstadoExistenteException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -5570029423839767906L;

	public EstadoExistenteException(String mensagem) {
		super(mensagem);
	}

	public EstadoExistenteException(String nome, String sigla) {
		this(String.format("J\u00e1 existe um estado com o nome %s e sigla %s cadastrado.", nome, sigla));
	}
}