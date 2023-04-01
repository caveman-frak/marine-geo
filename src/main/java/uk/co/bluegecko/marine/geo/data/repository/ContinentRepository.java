package uk.co.bluegecko.marine.geo.data.repository;

import java.util.Optional;
import uk.co.bluegecko.marine.geo.data.model.Continent;
import uk.co.bluegecko.marine.shared.data.repository.ListRepository;

public interface ContinentRepository extends ListRepository<Continent, Integer> {

	Optional<Continent> findByCode(String code);

}