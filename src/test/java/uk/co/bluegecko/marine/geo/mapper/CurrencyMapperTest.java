package uk.co.bluegecko.marine.geo.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import uk.co.bluegecko.marine.geo.data.model.Currency;

@SpringJUnitConfig
@Import(CurrencyMapperImpl.class)
class CurrencyMapperTest {

	@Autowired
	private CurrencyMapper mapper;

	@Test
	void mapFromModelToApi() {
		assertThat(mapper.toApi(Currency.builder().code("GBP").name("Pound Sterling").build()))
				.isEqualTo(uk.co.bluegecko.marine.wire.geo.Currency
						.builder().code("GBP").name("Pound Sterling").build());
	}

	@Test
	void mapFromApiToModel() {
		assertThat(mapper.fromApi(uk.co.bluegecko.marine.wire.geo.Currency
				.builder().code("GBP").name("Pound Sterling").build()))
				.isEqualTo(Currency.builder().code("GBP").name("Pound Sterling").build());
	}

}