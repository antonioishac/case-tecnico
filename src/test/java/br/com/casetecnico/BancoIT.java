package br.com.casetecnico;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.casetecnico.util.ResourceUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class BancoIT {

	private static final String VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE = "Viola\u00e7\u00e3o de regra de neg\u00f3cio";

    private static final String DADOS_INVALIDOS_PROBLEM_TITLE = "Dados inv\u00e1lidos";

    private static final int BANCO_ID_INEXISTENTE = 100000;

	private String jsonCorretoBancoTeste;

	private String jsonCorretoBancoSemEstadoTeste;

	private String jsonCorretoBancoComEstadoInexistenteTeste;

	@LocalServerPort
	private int port;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/api/bancos";

		jsonCorretoBancoTeste = ResourceUtils.getContentFromResource(
				"/json/correto/banco-teste.json");

		jsonCorretoBancoSemEstadoTeste = ResourceUtils.getContentFromResource(
                "/json/incorreto/banco-teste-sem-estado.json");

		jsonCorretoBancoComEstadoInexistenteTeste = ResourceUtils.getContentFromResource(
                "/json/incorreto/banco-teste-com-estado-invalido.json");
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarBanco() {
	    given()
	        .body(jsonCorretoBancoTeste)
	        .contentType(ContentType.JSON)
	        .accept(ContentType.JSON)
	    .when()
	        .post()
	    .then()
	        .statusCode(HttpStatus.CREATED.value());
	}

	@Test
    public void deveRetornarStatus400_QuandoCadastrarBancoSemEstado() {
        given()
            .body(jsonCorretoBancoSemEstadoTeste)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(DADOS_INVALIDOS_PROBLEM_TITLE));
    }

    @Test
    public void deveRetornarStatus400_QuandoCadastrarBancoComEstadoInexistente() {
        given()
            .body(jsonCorretoBancoComEstadoInexistenteTeste)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(VIOLACAO_DE_REGRA_DE_NEGOCIO_PROBLEM_TYPE));
    }

	@Test
	public void deveRetornarStatus200_QuandoConsultarBancos() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConterBancosComNomeAgenciaTeste1_e_AgenciaTeste2_QuandoConsultarBancos() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("content.nome", hasItems("Agencia Teste 1", "Agencia Teste 2"));
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarBancoExistente() {
		given()
			.pathParam("bancoId", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{bancoId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", equalTo("Agencia Teste 2"));
	}

	@Test
	public void deveRetornarStatus404_QuandoConsultarBancoInexistente() {
		given()
			.pathParam("bancoId", BANCO_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{bancoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

}
