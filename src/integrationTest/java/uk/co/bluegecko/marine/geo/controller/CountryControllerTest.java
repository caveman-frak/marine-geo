package uk.co.bluegecko.marine.geo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.bluegecko.marine.geo.data.model.Continent;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.data.model.Subcontinent;
import uk.co.bluegecko.marine.geo.handler.CountryHandler;
import uk.co.bluegecko.marine.geo.handler.ErrorHandler;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.geo.test.config.TestApplicationConfiguration;

@WebMvcTest(CountryController.class)
@ContextConfiguration(classes = {CountryController.class, CountryHandler.class,
		ErrorHandler.class, TestApplicationConfiguration.class})
class CountryControllerTest {

	@MockBean
	private CountryService countryService;

	@Autowired
	private MockMvc mockMvc;

	private final Continent backOfBeyond = Continent.builder().code("BoB").name("Back of Beyond").build();
	private final Subcontinent whoKnows =
			Subcontinent.builder().id(1).name("Who Knows").continent(backOfBeyond).build();
	private final List<Country> countries = List.of(
			Country.builder().code("GB").code3("GBR").name("Grate Britannia")
					.subcontinent(whoKnows)
					.nativeName("Arsehole of Europe").build(),
			Country.builder().code("US").code3("USA").name("Fractured States of Murica")
					.subcontinent(whoKnows)
					.nativeName("Something, something, Free World").build());

	@BeforeEach
	void setUp() {
		when(countryService.all()).thenReturn(countries.stream().toList());
		when(countryService.find(any(String.class))).thenReturn(Optional.empty());
		when(countryService.find(eq("GB"))).thenReturn(Optional.of(countries.get(0)));
	}

	@Test
	void testGetAll() throws Exception {
		mockMvc.perform(get("/country")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpectAll(
						jsonPath("$.length()").value(2),
						jsonPath("$.[0].code").value("GB"),
						jsonPath("$.[1].code").value("US"))
				.andReturn();
	}

	@Test
	void testFindExists() throws Exception {
		mockMvc.perform(get("/country/{id}", "GB")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code").value("GB"))
				.andExpect(jsonPath("$.name").value("Grate Britannia"))
				.andReturn();
	}

	@Test
	void testFindMissing() throws Exception {
		mockMvc.perform(get("/country/{id}", "DE")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andReturn();
	}

}