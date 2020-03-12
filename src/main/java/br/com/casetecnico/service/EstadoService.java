package br.com.casetecnico.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.casetecnico.domain.exception.EntidadeEmUsoException;
import br.com.casetecnico.domain.exception.EstadoExistenteException;
import br.com.casetecnico.domain.exception.EstadoNaoEncontradaException;
import br.com.casetecnico.domain.model.Estado;
import br.com.casetecnico.domain.repository.EstadoRepository;
import br.com.casetecnico.service.filter.EstadoFilter;

/**
 * @author aishac
 * Classe de servi\u00e7o para manipular os dados refer\u00eante a estados.
 *
 */
@Service
public class EstadoService {

	private static final String MSG_ESTADO_EM_USO
		= "Estado com o c\u00f3digo %d n\u00e3o pode ser removida, pois est\u00e1 em uso";

	@Autowired
	private EstadoRepository repository;

	/**
	 * M\u00e9todo para cadastrar um estado.
	 * @param estado
	 * @return Estado
	 */
	public Estado criar(Estado estado) {
		Optional<Estado> verificaEstado = repository.findByNomeAndSigla(estado.getNome(), estado.getSigla());
		if (verificaEstado.isPresent()) {
			throw new EstadoExistenteException(estado.getNome(), estado.getSigla());
		}
		return repository.saveAndFlush(estado);
	}

	/**
	 * M\u00e9todo para atualizar um estado espec\u00edfico.
	 * @param codigo
	 * @param estado
	 * @return Estado
	 */
	public Estado atualizar(Long codigo, Estado estado) {
		Optional<Estado> estadoSalvo = repository.findById(codigo);
		if (!estadoSalvo.isPresent()) {
			throw new EstadoNaoEncontradaException(codigo);
		}
		BeanUtils.copyProperties(estado, estadoSalvo.get(), "codigo");
		return repository.save(estadoSalvo.get());
	}

	/**
	 * M\u00e9todo que retorna uma lista de estados conforme par\u00e2metros.
	 * @param filter
	 * @param pageable
	 * @return Page<Estado>
	 */
	public Page<Estado> filtrarEstado(EstadoFilter filter, Pageable pageable) {
		Page<Estado> estados = repository.filtrar(filter, pageable);
		return estados;
	}

	/**
	 * M\u00e9todo que retorna um estado pelo c\u00f3digo informado.
	 * @param codigo
	 * @return Estado
	 */
	public Estado buscarEstadoPeloCodigo(Long codigo) {
		return repository.findById(codigo)
				.orElseThrow(() -> new EstadoNaoEncontradaException(codigo));
	}

	/**
	 * M\u00e9todo para remover um estado pelo par\u00e2metro do c\u00f3digo informado.
	 * @param estadoId
	 */
	public void excluir(Long estadoId) {
		try {
			repository.deleteById(estadoId);

		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
}
