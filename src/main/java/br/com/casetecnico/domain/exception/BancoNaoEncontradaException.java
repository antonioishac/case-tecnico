package br.com.casetecnico.domain.exception;

/**
 * @author aishac
 *
 */
public class BancoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -8708121392635642818L;

	public BancoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public BancoNaoEncontradaException(Long bancoId) {
		this(String.format("N\u00e3o existe um cadastro um banco com o c\u00f3digo %d", bancoId));
	}
}
