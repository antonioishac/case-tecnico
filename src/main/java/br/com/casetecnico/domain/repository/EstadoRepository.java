package br.com.casetecnico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casetecnico.domain.model.Estado;
import br.com.casetecnico.domain.repository.estado.EstadoRepositoryQuery;

/**
 * @author aishac
 * Reposit\u00f3rio para manipula\u00e7\u00e3o dos dados dos estados.
 *
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>, EstadoRepositoryQuery {

	/**
	 * M\u00e9todo que busca um estado pelo nome e sigla.
	 * @param nome
	 * @param sigla
	 * @return Optional<Estado>
	 */
	Optional<Estado> findByNomeAndSigla(String nome, String sigla);

}
