package lookids.mono.common.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lookids.favorite.favorite.domain.FavoriteType;

@Component
public class FavoriteTypeConverter implements Converter<String, FavoriteType> {

	@Override
	public FavoriteType convert(String source) {
		return FavoriteType.fromValue(source);
	}
}