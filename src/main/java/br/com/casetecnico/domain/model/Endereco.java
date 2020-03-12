package br.com.casetecnico.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import br.com.casetecnico.config.Groups;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

	@ApiModelProperty(value = "Nome do logradouro", example = "Rua da patrulha", required = true)
	@NotBlank(message = "{casetec-banco-logradouro}")
	@Column(name = "logradouro")
	private String logradouro;

	@ApiModelProperty(value = "Nome do bairro", example = "Jardim Santa Elena", required = false)
	@Column(name = "bairro")
	private String bairro;

	@ApiModelProperty(value = "Nome do cidade", example = "S\u00e3o Paulo", required = true)
	@NotBlank(message = "{casetec-banco-cidade}")
	@Column(name = "cidade")
	private String cidade;

	@ApiModelProperty(value = "Cep do banco", example = "57072277", required = false)
	@Column(name = "cep")
	private String cep;

	@ApiModelProperty(value = "Informe o estado do banco", required = false)
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
	@NotNull(message = "{casetec-banco-estado}")
	@ManyToOne
	@JoinColumn(name = "ESTADO_ID", nullable = false)
	private Estado estado;

}
