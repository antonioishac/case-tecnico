package br.com.casetecnico.domain.exception;

/**
 * @author aishac
 *
 */
public class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = -6372606416934636743L;

	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
