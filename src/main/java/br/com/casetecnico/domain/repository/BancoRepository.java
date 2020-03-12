package br.com.casetecnico.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.casetecnico.domain.model.Banco;
import br.com.casetecnico.domain.repository.banco.BancoRepositoryQuery;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long>, BancoRepositoryQuery {

	Optional<Banco> findByAgenciaAndNome(String agencia, String nome);

}
