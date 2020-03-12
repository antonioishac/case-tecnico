package br.com.casetecnico.api.controller;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.casetecnico.service.PlanetaService;
import br.com.casetecnico.service.dto.PlanetsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "API Externa que retorna uma lista de planetas.")
@RestController
@RequestMapping("api")
public class PlanetaApiExternaController {

	@Autowired
	private PlanetaService planetaService;

	@ApiOperation(value = "Consumo da API https://swapi.co/api/planets/ para retornar uma lista de planetas ", response = PlanetsDTO.class,
			notes = "Essa opera\u00e7\u00e3o consome uma API externa retornando uma lista de planetas.")
	@GetMapping({"/planetas/star-wars/{pagina}","/planetas/star-wars"})
	public PlanetsDTO listarPlanetaApi(@PathVariable(name = "pagina", required = false) Integer pagina) {
		Integer numeroPagina = pagina == null ? NumberUtils.INTEGER_ONE : pagina;
		PlanetsDTO planets = planetaService.listarPlanetasAPI(numeroPagina);
		return planets;
	}
}
