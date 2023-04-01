package uk.co.bluegecko.marine.geo.data.repository;

import uk.co.bluegecko.marine.geo.data.model.Currency;
import uk.co.bluegecko.marine.shared.data.repository.ListRepository;

import java.util.Optional;

public interface CurrencyRepository extends ListRepository<Currency, String> {

	Optional<Currency> findByNumericCode(int code);
	
}
