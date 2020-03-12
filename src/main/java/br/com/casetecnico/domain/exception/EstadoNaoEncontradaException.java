package br.com.casetecnico.domain.exception;

/**
 * @author aishac
 * Classe de exception que trata se o estado n\u00e3o for encontrado.
 *
 */
public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 2948990608709255062L;

	public EstadoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public EstadoNaoEncontradaException(Long estadoId) {
		this(String.format("N\u00e3o existe um cadastro de estado com o c\u00f3digo %d", estadoId));
	}
}