package com.axlkike.currency.services;
/**
 * Created by kikejf on 22/02/2016.
 */

import com.axlkike.currency.configuration.CacheConfig;
import com.axlkike.currency.domain.CurrencySearch;
import org.springframework.cache.annotation.Cacheable;

public interface IcurrencyLayerService {

	@Cacheable(CacheConfig.LONG_CACHE)
	public CurrencySearch getCurrencySearch(CurrencySearch currencySearch);
}
