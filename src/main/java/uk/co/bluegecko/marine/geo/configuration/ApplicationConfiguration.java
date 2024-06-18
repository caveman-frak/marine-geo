package uk.co.bluegecko.marine.geo.configuration;

import static org.apache.commons.text.WordUtils.capitalizeFully;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.co.bluegecko.marine.shared.configuration.SharedConfiguration;

/**
 * Configuration beans for general application use.
 */
@Configuration
public class ApplicationConfiguration extends SharedConfiguration {

	@Bean
	public OpenAPI customOpenAPI(
			@Value("${spring.application.name:Unknown Application}") String name,
			@Value("${app.description:Unknown Description}") String description,
			@Value("${app.version:Unknown Version}") String version,
			@Value("${app.licence:http://localhost:8080/license.txt}") String licence) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title(capitalizeFully(name)).description(description).version(version)
						.license(new License().name("M.I.T.").url(licence)));
	}

	@Bean
	String[] bundleNames() {
		return new String[]{
				"messages.geographic",
				"messages.country"};
	}

	@Override
	@Bean
	public MessageSource messageSource(String... bundleNames) {
		return super.messageSource(bundleNames);
	}

}