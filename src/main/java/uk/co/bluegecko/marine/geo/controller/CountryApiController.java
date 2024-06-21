package uk.co.bluegecko.marine.geo.controller;

import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.CODE;
import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.COUNTRY;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.event.Level;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.bluegecko.marine.geo.mapper.CountryMapper;
import uk.co.bluegecko.marine.geo.service.CountryService;
import uk.co.bluegecko.marine.shared.advice.Timed;
import uk.co.bluegecko.marine.wire.geo.Country;

/**
 * Read-only REST end-point for {@link Country}.
 */
@RestController
@RequestMapping(path = COUNTRY, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Tag(name = "${country-controller.name}", description = "${country-controller.description}")
public class CountryApiController {

	CountryService service;
	CountryMapper mapper;

	@GetMapping
	@Operation(operationId = "countries",
			summary = "${country-controller.get-all.summary}",
			description = "${country-controller.get-all.description}")
	@Timed
	public ResponseEntity<List<Country>> retrieveAll() {
		return ResponseEntity.ok(service.all().map(mapper::toApi).toList());
	}

	@GetMapping(CODE)
	@Operation(operationId = "country",
			summary = "${country-controller.get-one.summary}",
			description = "${country-controller.get-one.description}")
	@Timed(level = Level.WARN)
	public ResponseEntity<Country> retrieveByCode(
			@NotBlank @Size(min = 2, max = 3)
			@Parameter(description = "${country-controller.get-one.param.code}",
					example = "GB")
			@PathVariable String code) {
		return ResponseEntity.of(service.find(code).map(mapper::toApi));
	}

}