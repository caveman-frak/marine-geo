package uk.co.bluegecko.marine.geo.controller;

import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.COUNTRY;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.bluegecko.marine.geo.mapper.CountryMapper;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.shared.advice.Timed;
import uk.co.bluegecko.marine.wire.geo.Country;

/**
 * Read-only HTML endpoint for {@link Country}.
 */
@Controller
@RequestMapping(path = COUNTRY, produces = MediaType.TEXT_HTML_VALUE)
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CountryAppController {

	CountryService service;
	CountryMapper mapper;

	@GetMapping
	@Timed
	public String retrieveAll(Model model) {
		model.addAttribute("countries", service.all().map(mapper::toApi).toList());
		return "countries";
	}

}