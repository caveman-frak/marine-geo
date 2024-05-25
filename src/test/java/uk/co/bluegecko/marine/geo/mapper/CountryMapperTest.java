package uk.co.bluegecko.marine.geo.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import uk.co.bluegecko.marine.geo.data.model.Continent;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.data.model.Subcontinent;

@SpringJUnitConfig
@Import(CountryMapperImpl.class)
class CountryMapperTest {

	@Autowired
	CountryMapper mapper;

	@Test
	void mapFromModelToApi() {
		assertThat(mapper.toApi(Country.builder().code("GB").code3("GBR").name("Great Britain").nativeName("ArseEnd")
				.subcontinent(Subcontinent.builder().id(0).name("Western Europe").continent(Continent.builder()
						.id(0).code("EU").name("Europe").build()).build()).build()))
				.isEqualTo(uk.co.bluegecko.marine.wire.geo.Country.builder().code("GB")
						.name("Great Britain")
						.nativeName("ArseEnd")
						.continent(uk.co.bluegecko.marine.wire.geo.Continent.builder()
								.code("EU").name("Europe").build()).build());
	}

}