package uk.co.bluegecko.marine.geo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import uk.co.bluegecko.marine.geo.data.model.Country;
import uk.co.bluegecko.marine.shared.data.mapper.MapToApi;
import uk.co.bluegecko.marine.shared.data.mapper.MapperConfiguration;

@Mapper(config = MapperConfiguration.class)
public interface CountryMapper extends MapToApi<uk.co.bluegecko.marine.wire.geo.Country, Country> {


	@Mapping(target = "continent", source = "subcontinent.continent")
	@Override
	uk.co.bluegecko.marine.wire.geo.Country toApi(Country dataModel);

}