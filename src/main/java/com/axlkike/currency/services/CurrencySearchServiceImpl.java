package com.axlkike.currency.services;

/**
 * Created by kikejf on 19/01/2016.
 */
import com.axlkike.currency.domain.CurrencySearch;
import com.axlkike.currency.repositories.CurrencySearchRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CurrencySearchServiceImpl implements IcurrencySearchService {

    @Inject
    private CurrencySearchRepository currencySearchRepository;

    @Override
    public Iterable<CurrencySearch> listAllCurrencySearchesByUserId(String userId) {

        Iterable<CurrencySearch> cs = currencySearchRepository.findAllByUserId(userId);

        return cs;
    }

    @Override
    public void saveCurrencySearch(CurrencySearch currencySearch) {
        currencySearchRepository.save(currencySearch);
    }

    @Override
    public Iterable<CurrencySearch> listAllCurrencySearches() {
        return currencySearchRepository.findAll();
    }
}