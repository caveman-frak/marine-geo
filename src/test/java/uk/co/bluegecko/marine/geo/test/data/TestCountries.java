package uk.co.bluegecko.marine.geo.test.data;

import lombok.experimental.UtilityClass;
import uk.co.bluegecko.marine.wire.geo.Continent;
import uk.co.bluegecko.marine.wire.geo.Country;

import java.util.stream.Stream;

@UtilityClass
public class TestCountries {

	public static final Continent europe = Continent.builder().code("EU").name("Europe").build();
	public static final Continent northAmerica = Continent.builder().code("NA").name("North America").build();

	public static final Country unitedKingdom = Country.builder().continent(europe)
			.code("GB").name("United Kingdom").nativeName("United Kingdom").build();

	public static final Country unitedStates = Country.builder().continent(northAmerica)
			.code("US").name("United States of America").nativeName("United States of America").build();

	public static Stream<Country> countries() {
		return Stream.of(unitedKingdom, unitedStates);
	}
}
