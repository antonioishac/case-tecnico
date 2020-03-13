package br.com.casetecnico.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.casetecnico.domain.exception.BancoExistenteException;
import br.com.casetecnico.domain.exception.BancoNaoEncontradaException;
import br.com.casetecnico.domain.exception.EstadoNaoEncontradaException;
import br.com.casetecnico.domain.model.Banco;
import br.com.casetecnico.domain.model.Estado;
import br.com.casetecnico.domain.repository.BancoRepository;
import br.com.casetecnico.service.filter.BancoFilter;

/**
 * @author aishac
 * Classe de servi\u00e7o para manipular os dados de banco.
 *
 */
@Service
public class BancoService {

	@Autowired
	private BancoRepository repository;

	@Autowired
	private EstadoService estadoService;

	/**
	 * M\u00e9todo para criar um banco.
	 * @param banco
	 * @return Banco
	 */
	public Banco criar(Banco banco) {
		Optional<Banco> existeBanco = repository.findByAgenciaAndNome(banco.getAgencia(), banco.getNome());
		if (existeBanco.isPresent()) {
			throw new BancoExistenteException(banco.getAgencia(), banco.getNome());
		}
		Long estadoId = banco.getEndereco().getEstado().getCodigo();
		Estado estado = estadoService.buscarEstadoPeloCodigo(estadoId);
		banco.getEndereco().setEstado(estado);
		return repository.save(banco);
	}

	/**
	 * M\u00e9todo para atualizar um banco.
	 * @param codigo
	 * @param banco
	 * @return Banco
	 */
	public Banco atualizar(Long codigo, Banco banco) {
		Optional<Banco> bancoSalvo = repository.findById(codigo);
		if (!bancoSalvo.isPresent()) {
			throw new BancoNaoEncontradaException(codigo);
		}
		BeanUtils.copyProperties(banco, bancoSalvo.get(), "codigo", "endereco.estado", "dataCadastro");
		return repository.save(bancoSalvo.get());
	}

	/**
	 * M\u00e9todo que retorna uma lista de bancos paginado e filtrado conforme par\u00e2metros.
	 * @param filter
	 * @param pageable
	 * @return
	 */
	public Page<Banco> filtrarBanco(BancoFilter filter, Pageable pageable) {
		Page<Banco> bancos = repository.filtrar(filter, pageable);
		return bancos;
	}

	/**
	 * M\u00e9todo que realiza a busca de um banco pelo c\u00f3digo.
	 * @param codigo
	 * @return Banco
	 */
	public Banco buscarBancoPeloCodigo(Long codigo) {
		return repository.findById(codigo)
				.orElseThrow(() -> new BancoNaoEncontradaException(codigo));
	}

	/**
	 * M\u00e9todo para excluir um banco.
	 * @param estadoId
	 */
	public void excluir(Long estadoId) {
		try {
			repository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(estadoId);
		}
	}
}
