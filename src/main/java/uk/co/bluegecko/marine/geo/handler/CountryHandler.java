package uk.co.bluegecko.marine.geo.handler;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.wire.geo.Country;

@Service
@Value
public class CountryHandler {

	CountryService countryService;
	
	/**
	 * Find all {@link Country}.
	 *
	 * @return {@link ServerResponse} of {@link HttpStatus#OK} response with list of active vessels.
	 */
	public ServerResponse all() {
		return ServerResponse.ok().body(countryService.all());
	}

	/**
	 * Find and return the {@link Country} with the unique id.
	 *
	 * @param code the unique ISO code of the country.
	 * @return {@link ServerResponse} of {@link HttpStatus#OK} response with the vessel if it exists
	 * otherwise {@link HttpStatus#NOT_FOUND}.
	 */
	public ServerResponse find(String code) {
		return countryService.find(code)
				.map(country -> ServerResponse.ok().body(country))
				.orElse(ServerResponse.notFound().build());
	}

}
