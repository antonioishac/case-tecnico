package br.com.casetecnico;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.casetecnico.domain.exception.EntidadeEmUsoException;
import br.com.casetecnico.domain.exception.EstadoExistenteException;
import br.com.casetecnico.domain.exception.EstadoNaoEncontradaException;
import br.com.casetecnico.domain.model.Estado;
import br.com.casetecnico.service.EstadoService;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EstadoIntegrationTests {

	@Autowired
	private EstadoService service;

	@Test
	public void testarCadastroEstadoComSucesso() {
		Estado novoEstado = new Estado();
		novoEstado.setNome("Rio Grande so Sul");
		novoEstado.setSigla("RS");

		novoEstado = service.criar(novoEstado);

		assertThat(novoEstado).isNotNull();
		assertThat(novoEstado.getCodigo()).isNotNull();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testarCadastroCozinhaSemNome() {
		Estado novoEstado = new Estado();
		novoEstado.setNome(null);

		novoEstado = service.criar(novoEstado);
	}

	@Test(expected = EstadoNaoEncontradaException.class)
	public void testarEstadoNaoEncontrado() {
		service.buscarEstadoPeloCodigo(150L);
	}

	@Test(expected = EstadoExistenteException.class)
	public void testarCadastroUmEstadoExistente() {
		Estado novoEstado = new Estado();
		novoEstado.setNome("São Paulo");
		novoEstado.setSigla("SP");

		novoEstado = service.criar(novoEstado);
	}

	@Test(expected = EntidadeEmUsoException.class)
	public void testarExcluirEstadosEmUso() {
		service.excluir(1L);
	}

	@Test
	public void testarBuscarEstadoPeloCodigo() {
		Estado estado = service.buscarEstadoPeloCodigo(1L);
		assertThat(estado).isNotNull();
	}

}
