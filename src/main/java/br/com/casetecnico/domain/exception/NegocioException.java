package br.com.casetecnico.domain.exception;

/**
 * @author Antonio Ishac
 *
 */
public class NegocioException extends RuntimeException {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 859154729198338836L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}

	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
