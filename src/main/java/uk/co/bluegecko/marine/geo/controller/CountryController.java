package uk.co.bluegecko.marine.geo.controller;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.ACCEPT_JSON;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.handler.CountryHandler;
import uk.co.bluegecko.marine.geo.handler.ErrorHandler;

/**
 * Read-only REST end-point for {@link Country}.
 */
@Configuration(proxyBeanMethods = false)
public class CountryController {

	/**
	 * Router for the {@link Country} REST calls. Read-only service of static data.
	 *
	 * @param countryHandler handler for Country REST calls.
	 * @param errorHandler   error handler.
	 * @return Routing for Country REST calls.
	 */
	@Bean
	@RouterOperations({
			@RouterOperation(method = RequestMethod.GET, path = "/country", operation = @Operation(description =
					"Retrieve all countries", operationId = "AllCountries", tags = "Countries", responses =
			@ApiResponse(responseCode = "200", content = @Content(array = @ArraySchema(schema =
			@Schema(implementation = Country.class)))))),
			@RouterOperation(method = RequestMethod.GET, path = "/country/{code}", operation =
			@Operation(description = "Retrieve one country", parameters = @Parameter(name = "code", description =
					"Country code", required = true, in = ParameterIn.PATH, schema = @Schema(implementation =
					String.class, example = "GB")), operationId = "FindCountry", tags = "Countries", responses =
					{@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation =
							Country.class))), @ApiResponse(responseCode = "404", description = "Country not found")}
			))
	})
	public RouterFunction<ServerResponse> vesselRouting(CountryHandler countryHandler, ErrorHandler errorHandler) {
		return route().nest(RequestPredicates.path("/country"),
						builder -> {
							builder.before(errorHandler::logProcessingRequest);
							builder.GET("", ACCEPT_JSON, request ->
									countryHandler.all());
							builder.GET("/{code}", ACCEPT_JSON, request ->
									countryHandler.find(request.pathVariable("code")));
						}
				)
				.onError(IllegalArgumentException.class, (e, request) ->
						errorHandler.buildExceptionResponse(e, request, HttpStatus.BAD_REQUEST))
				.build();
	}
}