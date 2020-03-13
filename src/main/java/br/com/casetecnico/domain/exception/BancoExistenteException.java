package br.com.casetecnico.domain.exception;

public class BancoExistenteException extends NegocioException {

	private static final long serialVersionUID = 235943790099875220L;

	public BancoExistenteException(String mensagem) {
		super(mensagem);
	}

	public BancoExistenteException(String agencia, String nome) {
		this(String.format("J\u00e1 existe est\u00e1 agencia %s e nome %s cadastrado.", agencia, nome));
	}
}