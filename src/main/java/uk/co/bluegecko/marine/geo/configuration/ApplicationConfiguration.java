package uk.co.bluegecko.marine.geo.configuration;

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