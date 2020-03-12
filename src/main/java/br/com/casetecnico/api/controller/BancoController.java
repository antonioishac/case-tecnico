package br.com.casetecnico.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.casetecnico.api.event.RecursoCriadoEvent;
import br.com.casetecnico.domain.model.Banco;
import br.com.casetecnico.service.BancoService;
import br.com.casetecnico.service.filter.BancoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API referente a manuten\u00e7\u00e3o de bancos.")
@RestController
@RequestMapping("/api/bancos")
public class BancoController {

	@Autowired
	private BancoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@ApiOperation(value = "Cadastrar bancos", response = Banco.class,
		notes = "Essa opera\u00e7\u00e3o cadastra um estado")
	public ResponseEntity<Banco> criarBanco(@RequestBody @Valid Banco banco, HttpServletResponse response) {
		Banco bancoSalvo = service.criar(banco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, banco.getCodigo()));
		return ResponseEntity.ok(bancoSalvo);
	}

	@GetMapping
	@ApiOperation(value = "Listar bancos", response = Banco.class,
	notes = "Essa opera\u00e7\u00e3o lista todos os bancos ativos")
	public ResponseEntity<Page<Banco>> listarExamesAtivos(BancoFilter filter, Pageable pageable) {
		Page<Banco> bancos = service.filtrarBanco(filter, pageable);
		return bancos == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(bancos);
	}

	@PutMapping("/{codigo}")
	@ApiOperation(value = "Atualizar banco", response = Banco.class,
	notes = "Essa opera\u00e7\u00e3o atualiza um banco.")
	public ResponseEntity<Banco> atualizarBanco(@PathVariable Long codigo, @RequestBody Banco banco) {
		Banco bancoSalvo = service.atualizar(codigo, banco);
		return ResponseEntity.ok(bancoSalvo);
	}


	@GetMapping("/{codigo}")
	@ApiOperation(value = "Buscar banco pelo c\u00f3digo", response = Banco.class,
	notes = "Essa opera\u00e7\u00e3o busca pelo c\u00f3digo do estado.")
	public ResponseEntity<Banco> buscarBancoPeloCodigo(@PathVariable Long codigo) {
		Banco banco = service.buscarBancoPeloCodigo(codigo);
		return ResponseEntity.ok(banco);
	}

	@DeleteMapping("/{bancoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long bancoId) {
		service.excluir(bancoId);
	}

}
