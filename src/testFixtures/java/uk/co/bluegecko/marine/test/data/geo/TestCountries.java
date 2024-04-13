package uk.co.bluegecko.marine.test.data.geo;

import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import uk.co.bluegecko.marine.geo.data.model.Continent;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.data.model.Subcontinent;

@UtilityClass
public class TestCountries {

	private static final Continent BOB = Continent.builder().code("BoB").name("Back of Beyond").build();
	private static final Subcontinent NK =
			Subcontinent.builder().id(1).name("Who Knows").continent(BOB).build();
	public static final Country UK =
			Country.builder().code("GB").code3("GBR").name("Grate Britannia")
					.subcontinent(NK)
					.nativeName("Arsehole of Europe").build();
	public static final Country USA =
			Country.builder().code("US").code3("USA").name("Fractured States of Murica")
					.subcontinent(NK)
					.nativeName("Something, something, Free World").build();

	public static Stream<Country> countries() {
		return Stream.of(UK, USA);
	}
}