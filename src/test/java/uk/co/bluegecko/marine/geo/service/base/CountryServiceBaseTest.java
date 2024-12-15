package uk.co.bluegecko.marine.geo.service.base;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import uk.co.bluegecko.marine.geo.data.fixture.CountryFixture;
import uk.co.bluegecko.marine.geo.data.repository.CountryRepository;
import uk.co.bluegecko.marine.geo.service.CountryService;

@SpringJUnitConfig
@Import(CountryServiceBase.class)
class CountryServiceBaseTest {

	@MockitoBean
	CountryRepository repository;

	@Autowired
	private CountryService countryService;

	@BeforeEach
	void setUpRepository() {
		when(repository.findAll()).thenReturn(CountryFixture.countries().toList());
		when(repository.findById(any())).thenReturn(Optional.empty());
		when(repository.findByCode3(any())).thenReturn(Optional.empty());
		when(repository.findById("GB")).thenReturn(Optional.of(CountryFixture.UK));
		when(repository.findByCode3("GBR")).thenReturn(Optional.of(CountryFixture.UK));
	}

	@Test
	void testAll() {
		assertThat(countryService.all().toList())
				.hasSize(2)
				.contains(CountryFixture.UK, CountryFixture.USA);

		verify(repository).findAll();
	}

	@Test
	void testFindByGB() {
		assertThat(countryService.find("GB")).isPresent();

		verify(repository).findById("GB");
		verify(repository, never()).findByCode3("GB");
	}

	@Test
	void testFindByGBR() {
		assertThat(countryService.find("GBR")).isPresent();

		verify(repository).findById("GBR");
		verify(repository).findByCode3("GBR");
	}

}