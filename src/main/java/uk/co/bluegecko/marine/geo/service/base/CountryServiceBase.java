package uk.co.bluegecko.marine.geo.service.base;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.wire.geo.Country;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceBase implements CountryService {


	/**
	 * List of all {@link Country}.
	 *
	 * @return all Countries/
	 */
	@Override
	public List<Country> all() {
		return null;
	}

	/**
	 * Find a single {@link Country} by code.
	 *
	 * @param code the ISO code of the Country.
	 * @return an {@link Optional} containing the Country if found or {@link Optional#empty()}.
	 */
	@Override
	public Optional<Country> find(@NonNull String code) {
		return Optional.empty();
	}
}
