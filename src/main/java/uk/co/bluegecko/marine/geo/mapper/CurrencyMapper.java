package uk.co.bluegecko.marine.geo.mapper;

import org.mapstruct.Mapper;
import uk.co.bluegecko.marine.geo.data.model.Currency;
import uk.co.bluegecko.marine.shared.data.mapper.MapToAndFromApi;
import uk.co.bluegecko.marine.shared.data.mapper.MapperConfiguration;

@Mapper(config = MapperConfiguration.class)
public interface CurrencyMapper extends MapToAndFromApi<uk.co.bluegecko.marine.wire.geo.Currency, Currency> {

}