package uk.co.bluegecko.marine.geo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;
import uk.co.bluegecko.marine.geo.handler.CountryHandler;
import uk.co.bluegecko.marine.geo.handler.ErrorHandler;
import uk.co.bluegecko.marine.wire.geo.Country;

import static org.springframework.web.servlet.function.RouterFunctions.route;
import static uk.co.bluegecko.marine.geo.controller.ControllerConstants.ACCEPT_JSON;

/**
 * Read-only REST end-point for {@link Country}.
 */
@Configuration(proxyBeanMethods = false)
public class CountryController {

	/**
	 * Router for the {@link Country} REST calls.
	 * Read-only service of static data.
	 *
	 * @param countryHandler handler for Country REST calls.
	 * @param errorHandler   error handler.
	 * @return Routing for Country REST calls.
	 */
	@Bean
	public RouterFunction<ServerResponse> vesselRouting(CountryHandler countryHandler, ErrorHandler errorHandler) {
		return route().nest(RequestPredicates.path("/country"),
						builder -> {
							builder.before(errorHandler::logProcessingRequest);
							builder.GET("", ACCEPT_JSON, request ->
									countryHandler.all());
							builder.GET("/{id}", ACCEPT_JSON, request ->
									countryHandler.find(request.pathVariable("id")));
						}
				)
				.onError(IllegalArgumentException.class, (e, request) ->
						errorHandler.buildExceptionResponse(e, request, HttpStatus.BAD_REQUEST))
				.build();
	}
}
