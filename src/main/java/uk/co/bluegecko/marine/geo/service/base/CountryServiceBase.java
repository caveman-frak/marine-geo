package uk.co.bluegecko.marine.geo.service.base;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.Value;
import org.springframework.stereotype.Service;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.shared.data.repository.ListRepository;

@Service
@Value
public class CountryServiceBase implements CountryService {

	ListRepository<Country, String> countryRepository;

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