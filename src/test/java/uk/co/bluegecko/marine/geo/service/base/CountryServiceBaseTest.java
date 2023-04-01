package uk.co.bluegecko.marine.geo.service.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.repository.ListCrudRepository;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.geo.test.data.TestCountries;
import uk.co.bluegecko.marine.test.data.Generators;
import uk.co.bluegecko.marine.test.data.InMemoryRepository;
import uk.co.bluegecko.marine.wire.geo.Country;

import static org.assertj.core.api.Assertions.assertThat;

class CountryServiceBaseTest {

	private CountryService countryService;

	@BeforeEach
	void setUp() {
		ListCrudRepository<Country, String> countryRepository = new InMemoryRepository<>(
				Country::code, InMemoryRepository.noop(), Generators.noop(), TestCountries.countries());

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