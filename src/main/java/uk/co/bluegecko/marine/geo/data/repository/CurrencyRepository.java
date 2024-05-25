package uk.co.bluegecko.marine.geo.data.repository;

import java.util.Optional;
import uk.co.bluegecko.marine.geo.data.model.Currency;
import uk.co.bluegecko.marine.shared.data.repository.ListRepository;

public interface CurrencyRepository extends ListRepository<Currency, String> {

	Optional<Currency> findByNumericCode(int code);

}