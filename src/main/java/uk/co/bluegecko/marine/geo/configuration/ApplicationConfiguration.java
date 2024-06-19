package uk.co.bluegecko.marine.geo.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import uk.co.bluegecko.marine.geo.GeographicApplication;
import uk.co.bluegecko.marine.shared.SharedPackage;
import uk.co.bluegecko.marine.shared.configuration.SharedConfiguration;

/**
 * Configuration beans for general application use.
 */
@Configuration
@EntityScan(basePackageClasses = {SharedPackage.class, GeographicApplication.class})
@EnableJpaRepositories(basePackageClasses = {SharedPackage.class, GeographicApplication.class})
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