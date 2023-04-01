package uk.co.bluegecko.marine.geo.service.base;

import lombok.NonNull;
import lombok.Value;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Service;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.wire.geo.Country;

import java.util.List;
import java.util.Optional;

@Service
@Value
public class CountryServiceBase implements CountryService {

	ListCrudRepository<Country, String> countryRepository;

	/**
	 * List of all {@link Country}.
	 *
	 * @return all Countries/
	 */
	@Override
	public List<Country> all() {
		return countryRepository.findAll();
	}

	/**
	 * Find a single {@link Country} by code.
	 *
	 * @param code the ISO code of the Country.
	 * @return an {@link Optional} containing the Country if found or {@link Optional#empty()}.
	 */
	@Override
	public Optional<Country> find(@NonNull String code) {
		return countryRepository.findById(code);
	}
}
