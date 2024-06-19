package uk.co.bluegecko.marine.geo.service.base;

import java.util.Optional;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.actuate.audit.listener.AuditApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.geo.data.repository.CountryRepository;
import uk.co.bluegecko.marine.geo.service.CountryService;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CountryServiceBase implements CountryService {

	CountryRepository countryRepository;
	ApplicationEventPublisher publisher;

	/**
	 * List of all {@link Country}.
	 *
	 * @return all Countries/
	 */
	@Override
	public Stream<Country> all() {
		publisher.publishEvent(new AuditApplicationEvent("someone", "counties-all"));
		return countryRepository.findAll().stream();
	}

	/**
	 * Find a single {@link Country} by code.
	 *
	 * @param code either the 2 or 3 character ISO code of the Country.
	 * @return an {@link Optional} containing the Country if found or {@link Optional#empty()}.
	 */
	@Override
	public Optional<Country> find(@NonNull String code) {
		publisher.publishEvent(new AuditApplicationEvent("someone", "countries-one", "id=" + code));
		return countryRepository.findById(code).or(() -> countryRepository.findByCode3(code));
	}
}