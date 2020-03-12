package br.com.casetecnico.domain.repository.banco;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.casetecnico.domain.model.Banco;
import br.com.casetecnico.domain.model.Endereco;
import br.com.casetecnico.service.filter.BancoFilter;

public class BancoRepositoryImpl implements BancoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Banco> filtrar(BancoFilter bancoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Banco> criteria = builder.createQuery(Banco.class);
		Root<Banco> root = criteria.from(Banco.class);

		Predicate[] predicates = criarRestricoes(bancoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Banco> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(bancoFilter));
	}

	private Predicate[] criarRestricoes(BancoFilter bancoFilter, CriteriaBuilder builder, Root<Banco> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(bancoFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get("nome")), "%" + bancoFilter.getNome() + "%"));
		}

		if (!StringUtils.isEmpty(bancoFilter.getAgencia())) {
			predicates.add(builder.equal(root.get("agencia"), bancoFilter.getAgencia()));
		}

		if (!StringUtils.isEmpty(bancoFilter.getCidade())) {
			predicates.add(builder.equal(root.get("endereco").get("cidade"), bancoFilter.getCidade()));
		}

		if (!StringUtils.isEmpty(bancoFilter.getCep())) {
			predicates.add(builder.equal(root.get("endereco").get("cep"), bancoFilter.getCep()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Banco> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}

	private Long total(BancoFilter bancoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Banco> root = criteria.from(Banco.class);

		Predicate[] predicates = criarRestricoes(bancoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
