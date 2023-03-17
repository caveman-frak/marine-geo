package uk.co.bluegecko.marine.geo.data.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.co.bluegecko.marine.geo.data.model.Currency;

import java.util.Objects;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.bluegecko.marine.test.jassert.Conditions.extract;

@DataJpaTest
public class CurrencyRepositoryTest {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Test
	void testFindAll() {
		assertThat(currencyRepository.findAll())
				.isNotEmpty()
				.extracting(Currency::code)
				.contains("GBP", "USD", "EUR");
	}

	@Test
	void testFindGBP() {
		assertThat(currencyRepository.findById("GBP"))
				.isPresent().get()
				.has(allOf(
						extract(Currency::code, "code", String::equals, "equal to", "GBP"),
						extract(Currency::name, "name", String::equals, "equal to", "Pound Sterling"),
						extract(Currency::numericCode, "numeric", Integer::equals, "equal to", 826),
						extract(Currency::minor, "minor", Integer::equals, "equal to", 2),
						extract(Currency::symbol, "symbol", String::equals, "equal to", "£")
				));
	}

	@Test
	void testFindDJF() {
		assertThat(currencyRepository.findById("DJF"))
				.isPresent().get()
				.has(allOf(
						extract(Currency::code, "code", String::equals, "equal to", "DJF"),
						extract(Currency::name, "name", String::equals, "equal to", "Djibouti Franc"),
						extract(Currency::numericCode, "numeric", Integer::equals, "equal to", 262),
						extract(Currency::minor, "minor", Integer::equals, "equal to", 0),
						extract(Currency::symbol, "symbol", Objects::isNull, "is null")
				));
	}

	@Test
	void testFindUSN() {
		// USN (US Next Day) is a tradeable symbol but not an actual currency, so should not be included
		assertThat(currencyRepository.findById("USN"))
				.isEmpty();
	}

	@Test
	void findByNumericCode() {
		assertThat(currencyRepository.findByNumericCode(978))
				.isPresent().get()
				.has(extract(Currency::code, "code", String::equals, "equal to", "EUR"));
	}
	
}
