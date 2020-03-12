package br.com.casetecnico.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.casetecnico.config.Groups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author aishac
 * Entidade que representa a tabela TB_ESTADO da base de dados.
 *
 */
@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "tb_estado")
public class Estado implements Serializable {

	private static final long serialVersionUID = -862063339313509465L;

	@NotNull(groups = Groups.EstadoId.class, message = "{casetec-estado-codigo}")
	@ApiModelProperty(readOnly = true)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long codigo;

	@ApiModelProperty(value = "Nome do estado", example = "S\u00e3o Paulo", required = true)
	@NotBlank(message = "{casetec-estado-nome}")
	@Column(name = "NOME")
	private String nome;

	@ApiModelProperty(value = "Sigla do estado", example = "SP", required = true)
	@NotBlank(message = "{casetec-estado-sigla}")
	@Length(min = 2, max = 2, message = "{casetec-estado-sigla-min-max}")
	@Column(name = "SIGLA")
	private String sigla;

}
