package uk.co.bluegecko.marine.geo.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.CODE;
import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.COUNTRY;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import uk.co.bluegecko.marine.geo.data.fixture.CountryFixture;
import uk.co.bluegecko.marine.geo.mapper.CountryMapper;
import uk.co.bluegecko.marine.geo.mapper.CountryMapperImpl;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.shared.configuration.TestApplicationConfiguration;

@WebMvcTest(CountryApiController.class)
@ContextConfiguration(classes = {CountryApiController.class, CountryMapperImpl.class,
		TestApplicationConfiguration.class})
class CountryApiControllerTest {

	@MockitoBean
	private CountryService countryService;

	@Autowired
	private CountryMapper mapper;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUpCountryService() {
		when(countryService.all()).thenReturn(Stream.of(CountryFixture.UK, CountryFixture.USA));
		when(countryService.find(any(String.class))).thenReturn(Optional.empty());
		when(countryService.find(eq("GB"))).thenReturn(Optional.of(CountryFixture.UK));
	}

	@Test
	void testGetAll() throws Exception {
		mockMvc.perform(get(COUNTRY)
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
		mockMvc.perform(get(COUNTRY + CODE, "GB")
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
		mockMvc.perform(get(COUNTRY + CODE, "DE")
						.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotFound())
				.andReturn();
	}

}