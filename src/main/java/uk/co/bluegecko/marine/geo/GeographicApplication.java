package uk.co.bluegecko.marine.geo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.bluegecko.marine.shared.application.AbstractApplication;

@SpringBootApplication
public class GeographicApplication extends AbstractApplication {

	public static void main(String[] args) {
		run(GeographicApplication.class, args);
	}
}