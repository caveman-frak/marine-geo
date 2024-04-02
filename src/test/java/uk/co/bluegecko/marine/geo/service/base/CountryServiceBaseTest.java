package uk.co.bluegecko.marine.geo.service.base;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.geo.test.data.TestCountries;
import uk.co.bluegecko.marine.shared.data.repository.ListRepository;
import uk.co.bluegecko.marine.test.data.Generators;
import uk.co.bluegecko.marine.test.data.InMemoryRepository;

class CountryServiceBaseTest {

	private CountryService countryService;

	@BeforeEach
	void setUp() {
		ListRepository<Country, String> countryRepository = new InMemoryRepository<>(
				Country::getCode, InMemoryRepository.noop(), Generators.noop(), TestCountries.countries());

		countryService = new CountryServiceBase(countryRepository);
	}

	@Test
	void testAll() {
		assertThat(countryService.all()).hasSize(2);
	}

	@Test
	void testFind() {
		assertThat(countryService.find("GB")).isPresent();
	}
}