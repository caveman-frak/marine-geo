package uk.co.bluegecko.marine.geo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class GeographicApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(GeographicApplication.class).registerShutdownHook(true).run(args);
	}
}