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
import br.com.casetecnico.domain.model.Estado;
import br.com.casetecnico.service.EstadoService;
import br.com.casetecnico.service.filter.EstadoFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API referente a manuten\u00e7\u00e3o de estados.")
@RestController
@RequestMapping("/api/estados")
public class EstadoController {

	@Autowired
	private EstadoService service;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@ApiOperation(value = "Cadastrar estados", response = Estado.class,
		notes = "Essa opera\u00e7\u00e3o cadastra um estado")
	public ResponseEntity<Estado> criarEstado(@RequestBody @Valid Estado estado, HttpServletResponse response) {
		Estado estadoSalvo = service.criar(estado);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estado.getCodigo()));
		return ResponseEntity.ok(estadoSalvo);
	}

	@GetMapping
	@ApiOperation(value = "Listar estados", response = Estado.class,
	notes = "Essa opera\u00e7\u00e3o lista todos os estados")
	public ResponseEntity<Page<Estado>> listarExamesAtivos(EstadoFilter filter, Pageable pageable) {
		Page<Estado> estados = service.filtrarEstado(filter, pageable);
		return estados == null ? ResponseEntity.noContent().build() : ResponseEntity.ok(estados);
	}

	@PutMapping("/{codigo}")
	@ApiOperation(value = "Atualizar estado", response = Estado.class,
	notes = "Essa opera\u00e7\u00e3o atualiza um estado.")
	public ResponseEntity<Estado> atualizarEstado(@PathVariable Long codigo, @RequestBody Estado estado) {
		Estado estadoSalvo = service.atualizar(codigo, estado);
		return ResponseEntity.ok(estadoSalvo);
	}


	@GetMapping("/{codigo}")
	@ApiOperation(value = "Buscar estado pelo c\u00f3digo", response = Estado.class,
	notes = "Essa opera\u00e7\u00e3o busca pelo c\u00f3digo do estado.")
	public ResponseEntity<Estado> buscarEstadoPeloCodigo(@PathVariable Long codigo) {
		Estado estado = service.buscarEstadoPeloCodigo(codigo);
		return ResponseEntity.ok(estado);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		service.excluir(estadoId);
	}

}
