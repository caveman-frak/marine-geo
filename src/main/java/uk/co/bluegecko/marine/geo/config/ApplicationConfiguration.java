package uk.co.bluegecko.marine.geo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import java.time.Clock;
import java.util.Random;
import java.util.random.RandomGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Configuration beans for general application use.
 */
@Component
public class ApplicationConfiguration {

	/**
	 * Standard Clock instance.
	 *
	 * @return default to {@link Clock#systemUTC()} .
	 */
	@Bean
	public Clock clock() {
		return Clock.systemUTC();
	}

	/**
	 * Standard Random instance.
	 *
	 * @return default to {@link Random}.
	 */
	@Bean
	public RandomGenerator randomGenerator() {
		return new Random();
	}

	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("Marine Geo Server").version(appVersion)
						.license(new License().name("M.I.T.").url("http://springdoc.org")));
	}

}