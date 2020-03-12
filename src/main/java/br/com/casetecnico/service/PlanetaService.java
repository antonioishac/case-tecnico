package br.com.casetecnico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.casetecnico.service.dto.PlanetsDTO;

/**
 * @author aishac
 * Classe de servi\u00e7o para consultar uma API externa.
 *
 */
@Service
public class PlanetaService {

	/**
	 * Constante com o endere\u00e7o da API externa
	 */
	private static final String BASE_URL = "https://swapi.co/api/planets/";

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Método que consome uma API externa e retorna uma lista de planetas.
	 * @param numeroPagina
	 * @return PlanetsDTO
	 */
	public PlanetsDTO listarPlanetasAPI(Integer numeroPagina) {
        final String url = BASE_URL + "?page=" + numeroPagina;
        return restTemplate.getForObject(url, PlanetsDTO.class);
	}

}
