package uk.co.bluegecko.marine.geo.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.bluegecko.marine.geo.data.model.Currency;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, String> {

	Optional<Currency> findByNumericCode(int code);
}
