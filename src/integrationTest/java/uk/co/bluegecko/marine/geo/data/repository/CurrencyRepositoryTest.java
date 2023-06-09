package uk.co.bluegecko.marine.geo.data.repository;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.bluegecko.marine.test.jassert.Conditions.extracted;

import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.co.bluegecko.marine.geo.data.model.Currency;

@DataJpaTest
public class CurrencyRepositoryTest {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Test
	void testFindAll() {
		assertThat(currencyRepository.findAll())
				.isNotEmpty()
				.extracting(Currency::getCode)
				.contains("GBP", "USD", "EUR");
	}

	@Test
	void testFindGBP() {
		assertThat(currencyRepository.findById("GBP"))
				.isPresent().get()
				.has(allOf(
						extracted(Currency::getCode, "code", String::equals, "equal to", "GBP"),
						extracted(Currency::getName, "name", String::equals, "equal to", "Pound Sterling"),
						extracted(Currency::getNumericCode, "numeric", Integer::equals, "equal to", 826),
						extracted(Currency::getMinor, "minor", Integer::equals, "equal to", 2),
						extracted(Currency::getSymbol, "symbol", String::equals, "equal to", "£")
				));
	}

	@Test
	void testFindDJF() {
		assertThat(currencyRepository.findById("DJF"))
				.isPresent().get()
				.has(allOf(
						extracted(Currency::getCode, "code", String::equals, "equal to", "DJF"),
						extracted(Currency::getName, "name", String::equals, "equal to", "Djibouti Franc"),
						extracted(Currency::getNumericCode, "numeric", Integer::equals, "equal to", 262),
						extracted(Currency::getMinor, "minor", Integer::equals, "equal to", 0),
						extracted(Currency::getSymbol, "symbol", Objects::isNull, "is null")
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
				.has(extracted(Currency::getCode, "code", String::equals, "equal to", "EUR"));
	}

}