package br.com.casetecnico.service.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlanetsDTO {

	@ApiModelProperty(value = "count", example = "777")
	private Integer count;

	@ApiModelProperty(value = "next", example = "https://swapi.co/api/planets/?page=5")
	private String next;

	@ApiModelProperty(value = "previous", example = "https://swapi.co/api/planets/?page=3")
	private String previous;

	@ApiModelProperty(value = "results")
	@JsonProperty("results")
	private List<PlanetDTO> planets;

}
