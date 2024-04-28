package uk.co.bluegecko.marine.geo.controller;

import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.COUNTRY;

import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.co.bluegecko.marine.geo.mapper.CountryMapper;
import uk.co.bluegecko.marine.geo.service.CountryService;

/**
 * Read-only HTML endpoint for {@link uk.co.bluegecko.marine.wire.geo.Country}.
 */
@Value
@Controller
@RequestMapping(path = COUNTRY, produces = MediaType.TEXT_HTML_VALUE)
public class CountryAppController {

	CountryService service;
	CountryMapper mapper;

	@GetMapping
	public String retrieveAll(Model model) {
		model.addAttribute("countries", service.all().map(mapper::toApi).toList());
		return "countries";
	}

}