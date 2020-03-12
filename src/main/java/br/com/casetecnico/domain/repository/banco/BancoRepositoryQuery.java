package br.com.casetecnico.domain.repository.banco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.casetecnico.domain.model.Banco;
import br.com.casetecnico.service.filter.BancoFilter;

public interface BancoRepositoryQuery {

	public Page<Banco> filtrar(BancoFilter exameFilter, Pageable pageable);

}
