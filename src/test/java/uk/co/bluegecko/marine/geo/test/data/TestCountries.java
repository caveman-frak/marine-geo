package uk.co.bluegecko.marine.geo.test.data;

import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import uk.co.bluegecko.marine.geo.data.model.Continent;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.data.model.Subcontinent;

@UtilityClass
public class TestCountries {

	private static final Continent backOfBeyond = Continent.builder().code("BoB").name("Back of Beyond").build();
	private static final Subcontinent whoKnows =
			Subcontinent.builder().id(1).name("Who Knows").continent(backOfBeyond).build();
	private static final Country unitedKingdom =
			Country.builder().code("GB").code3("GBR").name("Grate Britannia")
					.subcontinent(whoKnows)
					.nativeName("Arsehole of Europe").build();
	private static final Country unitedStates =
			Country.builder().code("US").code3("USA").name("Fractured States of Murica")
					.subcontinent(whoKnows)
					.nativeName("Something, something, Free World").build();

	public static Stream<Country> countries() {
		return Stream.of(unitedKingdom, unitedStates);
	}
}