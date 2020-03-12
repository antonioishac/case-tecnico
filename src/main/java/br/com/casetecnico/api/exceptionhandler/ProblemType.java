package br.com.casetecnico.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	DADOS_INVALIDOS("/dados-invalidos", "Dados inv\u00e1lidos"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Par\u00e2metro inv\u00e1lido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreens\u00edvel"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrada", "Recurso n\u00e3o encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Viola\u00e7\u00e3o de regra de neg\u00f3cio");

	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "http://localhost:8083" + path;
		this.title = title;
	}
}