package br.com.casetecnico.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author aishac
 * Entidade que representa a tabela TB_BANCO da base de dados.
 *
 */
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tb_banco")
public class Banco implements Serializable {

	private static final long serialVersionUID = -2405076395852120830L;

	@ApiModelProperty(readOnly = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long codigo;

	@ApiModelProperty(value = "Nome do banco", example = "Banco Suporte", required = true)
	@NotBlank(message = "{casetec-banco-nome}")
	@Column(name = "NOME")
	private String nome;

	@ApiModelProperty(value = "Nome da Agencia", example = "000125-7", required = true)
	@NotBlank(message = "{casetec-banco-agencia}")
	@Size(max = 20, message = "{casetec-banco-agencia-max}")
	@Column(name = "AGENCIA", length = 20)
	private String agencia;

	@Valid
	@Embedded
	private Endereco endereco;

	@JsonIgnore
	@CreationTimestamp
	@Column(name = "DATA_CADASTRO", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;

	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "DATA_ATUALIZACAO", nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizacao;

}
