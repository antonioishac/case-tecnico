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
import br.com.casetecnico.domain.repository.BancoRepository;
import br.com.casetecnico.service.filter.BancoFilter;

@Service
public class BancoService {

	@Autowired
	private BancoRepository repository;

	public Banco criar(Banco banco) {
		Optional<Banco> verificaBanco = repository.findByAgenciaAndNome(banco.getAgencia(), banco.getNome());
		if (verificaBanco.isPresent()) {
			throw new BancoExistenteException(banco.getAgencia(), banco.getNome());
		}
		return repository.save(banco);
	}

	public Banco atualizar(Long codigo, Banco banco) {
		Optional<Banco> bancoSalvo = repository.findById(codigo);
		if (!bancoSalvo.isPresent()) {
			throw new BancoNaoEncontradaException(codigo);
		}
		BeanUtils.copyProperties(banco, bancoSalvo.get(), "codigo");
		return repository.save(bancoSalvo.get());
	}

	public Page<Banco> filtrarBanco(BancoFilter filter, Pageable pageable) {
		Page<Banco> bancos = repository.filtrar(filter, pageable);
		return bancos;
	}

	public Banco buscarBancoPeloCodigo(Long codigo) {
		return repository.findById(codigo)
				.orElseThrow(() -> new BancoNaoEncontradaException(codigo));
	}

	public void excluir(Long estadoId) {
		try {
			repository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradaException(estadoId);
		}
	}
}
