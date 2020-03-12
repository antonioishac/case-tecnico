package br.com.casetecnico.service.mapper;

import java.util.List;

/**
 * @author aishac
 *
 * @param <DTO>
 * @param <ENTIDADE>
 */
public interface EntityMapper<D, E> {

	/**
	 * Metodo que realiza a conversao de entidade para DTO
	 *
	 * @param dto
	 * @return toEntity
	 */
	E toEntity(D dto);

    /**
     * Metodo que realiza a conversao de DTO para entidade
     *
     * @param entity
     * @return toDto
     */
    D toDto(E entity);

    /**
     * Metodo que realiza a conversao de uma lista de DTO para retornar uma lista de entidade
     *
     * @param dtoList
     * @return toEntity
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Metodo que realiza a conversao de uma lista de Entidade para retornar uma lista de DTO
     *
     * @param entityList
     * @return toDto
     */
    List<D> toDto(List<E> entityList);

}