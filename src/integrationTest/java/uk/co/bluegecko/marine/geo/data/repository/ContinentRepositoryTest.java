package uk.co.bluegecko.marine.geo.data.repository;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.bluegecko.marine.test.jassert.Conditions.extracted;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import uk.co.bluegecko.marine.geo.data.model.Continent;

@DataJpaTest
public class ContinentRepositoryTest {

	@Autowired
	private ContinentRepository continentRepository;

	@Test
	void testFindAll() {
		assertThat(continentRepository.findAll())
				.isNotEmpty()
				.hasSize(7)
				.extracting(Continent::code)
				.containsExactly("AN", "AF", "OC", "SA", "NA", "AS", "EU");
	}

	@Test
	void testFindEuropeById() {
		assertThat(continentRepository.findById(150))
				.isPresent().get()
				.has(allOf(
						extracted(Continent::code, "code", String::equals, "equal to", "EU"),
						extracted(Continent::name, "name", String::equals, "equal to", "Europe")
				));
	}

	@Test
	void testFindEuropeByCode() {
		assertThat(continentRepository.findByCode("EU"))
				.isPresent().get()
				.has(allOf(
						extracted(Continent::code, "code", String::equals, "equal to", "EU"),
						extracted(Continent::name, "name", String::equals, "equal to", "Europe")
				));
	}

	@Test
	void testFindMissingById() {
		assertThat(continentRepository.findById(0))
				.isEmpty();
	}

	@Test
	void testFindMissingByCode() {
		assertThat(continentRepository.findByCode("ZZ"))
				.isEmpty();
	}

}