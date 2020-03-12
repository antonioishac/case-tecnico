package br.com.casetecnico.domain.repository.estado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.casetecnico.domain.model.Estado;
import br.com.casetecnico.service.filter.EstadoFilter;

/**
 * @author aishac
 * Interface para busca de estados.
 *
 */
public interface EstadoRepositoryQuery {

	/**
	 * Assinatura para retornar uma lista de estados conforme os par\u00e2metros passados.
	 * @param exameFilter
	 * @param pageable
	 * @return Page<Estado>
	 */
	public Page<Estado> filtrar(EstadoFilter exameFilter, Pageable pageable);

}
