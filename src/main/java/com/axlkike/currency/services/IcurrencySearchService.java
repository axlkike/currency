package com.axlkike.currency.services;

/**
 * Created by kikejf on 19/01/2016.
 */
import com.axlkike.currency.domain.CurrencySearch;

public interface IcurrencySearchService {

    Iterable<CurrencySearch> listAllCurrencySearches();

    Iterable<CurrencySearch> listAllCurrencySearchesByUserId(String userId);

    void saveCurrencySearch(CurrencySearch currencySearch);

}