package uk.co.bluegecko.marine.geo.service;

import lombok.NonNull;
import uk.co.bluegecko.marine.wire.geo.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {

	/**
	 * List of all {@link Country}.
	 *
	 * @return all Countries/
	 */
	List<Country> all();

	/**
	 * Find a single {@link Country} by code.
	 *
	 * @param code the ISO code of the Country.
	 * @return an {@link Optional} containing the Country if found or {@link Optional#empty()}.
	 */
	Optional<Country> find(@NonNull String code);

}
