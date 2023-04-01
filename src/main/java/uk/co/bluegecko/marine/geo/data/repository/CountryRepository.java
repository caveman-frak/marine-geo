package uk.co.bluegecko.marine.geo.data.repository;

import java.util.Optional;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.shared.data.repository.ListRepository;

public interface CountryRepository extends ListRepository<Country, String> {

	Optional<Country> findByNumericCode(int code);

	Optional<Country> findByCode3(String code);

}