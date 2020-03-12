package br.com.casetecnico.service.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PlanetDTO {

	@ApiModelProperty(value = "name", example = "Corellia")
	private String name;

	@ApiModelProperty(value = "rotationPeriod", example = "25")
	@JsonProperty("rotation_period")
	private String rotationPeriod;

	@ApiModelProperty(value = "orbitalPeriod", example = "329")
	@JsonProperty("orbital_period")
	private String orbitalPeriod;

	@ApiModelProperty(value = "diameter", example = "11000")
	private String diameter;

	@ApiModelProperty(value = "climate", example = "temperate")
	private String climate;

	@ApiModelProperty(value = "gravity", example = "1 standard")
	private String gravity;

	@ApiModelProperty(value = "terrain", example = "plains, urban, hills, forests")
	private String terrain;

	@ApiModelProperty(value = "surfaceWater", example = "70")
	@JsonProperty("surface_water")
	private String surfaceWater;

	@ApiModelProperty(value = "population", example = "3000000000")
	private String population;

	@ApiModelProperty(value = "residents", example = "https://swapi.co/api/people/14/, https://swapi.co/api/people/18/")
	private List<String> residents;

	@ApiModelProperty(value = "films", example = "https://swapi.co/api/films/6/ https://swapi.co/api/films/1")
	private List<String> films;

	@ApiModelProperty(value = "created", example = "2014-12-10T16:49:12.453")
	private LocalDateTime created;

	@ApiModelProperty(value = "edited", example = "2014-12-20T20:58:18.456")
	private LocalDateTime edited;

	@ApiModelProperty(value = "url", example = "https://swapi.co/api/planets/22/")
	private String url;

}
