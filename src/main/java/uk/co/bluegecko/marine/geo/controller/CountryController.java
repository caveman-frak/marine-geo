package uk.co.bluegecko.marine.geo.controller;

import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.CODE;
import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.COUNTRY;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.bluegecko.marine.geo.mapper.CountryMapper;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.wire.geo.Country;

/**
 * Read-only REST end-point for {@link Country}.
 */
@Value
@RestController
@RequestMapping(COUNTRY)
public class CountryController {

	CountryService service;
	CountryMapper mapper;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retrieve all countries", description = "Retrieve all valid countries")
	public ResponseEntity<List<Country>> retrieveAll() {
		return ResponseEntity.ok(service.all().map(mapper::toApi).toList());
	}

	@GetMapping(path = CODE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary = "Retrieve one country", description = "Retrieve one country by country code")
	public ResponseEntity<Country> retrieveByCode(
			@NotBlank @Size(min = 2, max = 3)
			@Parameter(description = "The country code", example = "GB") @PathVariable String code) {
		return ResponseEntity.of(service.find(code).map(mapper::toApi));
	}

}