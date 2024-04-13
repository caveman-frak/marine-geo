package uk.co.bluegecko.marine.geo.service;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.NonNull;
import uk.co.bluegecko.marine.geo.data.model.Country;

public interface CountryService {

	/**
	 * Stream of all {@link Country}.
	 *
	 * @return all Countries/
	 */
	Stream<Country> all();

	/**
	 * Find a single {@link Country} by code.
	 *
	 * @param code the ISO code of the Country.
	 * @return an {@link Optional} containing the Country if found or {@link Optional#empty()}.
	 */
	Optional<Country> find(@NonNull String code);

}