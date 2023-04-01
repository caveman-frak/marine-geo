package uk.co.bluegecko.marine.geo.data.repository;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.bluegecko.marine.test.jassert.Conditions.extracted;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.co.bluegecko.marine.geo.data.model.Continent;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.data.model.Subcontinent;

@DataJpaTest
public class CountryRepositoryTest {

	@Autowired
	private CountryRepository countryRepository;

	@Test
	void testFindAll() {
		assertThat(countryRepository.findAll())
				.isNotEmpty()
				.hasSize(249)
				.extracting(Country::code)
				.contains("GB", "US", "FR");
	}

	@Test
	void testFindGB() {
		assertThat(countryRepository.findById("GB"))
				.isPresent().get()
				.has(allOf(
						extracted(Country::code, "code", String::equals, "equal to", "GB"),
						extracted(Country::name, "name", String::equals, "equal to",
								"United Kingdom of Great Britain and Northern Ireland"),
						extracted(Country::numericCode, "numeric code", Integer::equals, "equal to", 826),
						extracted(Country::nativeName, "minor", String::equals, "equal to", "United Kingdom"),
						extracted(Country::subcontinent, "continent", Object::equals, "equal to",
								Subcontinent.builder().id(154).name("Northern Europe").continent(
										Continent.builder().code("EU").id(150).name("Europe").build()).build())
				));
	}

	@Test
	void testFindZZ() {
		// USN (US Next Day) is a tradeable symbol but not an actual currency, so should not be included
		assertThat(countryRepository.findById("ZZ"))
				.isEmpty();
	}

	@Test
	void findByCode3() {
		assertThat(countryRepository.findByCode3("GBR"))
				.isPresent().get()
				.has(extracted(Country::code, "code", String::equals, "equal to", "GB"));
	}

	@Test
	void findByNumericCode() {
		assertThat(countryRepository.findByNumericCode(826))
				.isPresent().get()
				.has(extracted(Country::code, "code", String::equals, "equal to", "GB"));
	}

}