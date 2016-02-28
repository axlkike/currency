package com.axlkike.currency.service;

import com.axlkike.currency.CurrencyApplication;
import com.axlkike.currency.domain.CurrencySearch;
import com.axlkike.currency.services.IcurrencySearchService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyApplication.class)
@IntegrationTest
@Transactional
public class CurrencySearchServiceTests {

    private int totalSearches = 0;
    private String userName = "user1";

	@Inject
	private IcurrencySearchService currencySearchService;

	@Before
	public void setup() {
        totalSearches = ((List<CurrencySearch>)currencySearchService.listAllCurrencySearches()).size();
	}

    @Test
    public void testFindAll() {

        assertThat(currencySearchService.listAllCurrencySearches()).hasSize(totalSearches);
    }

    @Test
    public void testSave() {
        CurrencySearch c = new CurrencySearch("user1", new DateTime(2011, 1, 20, 19, 20), "USD",
                                              "EUR", false, new BigDecimal("1.098300"));
        currencySearchService.saveCurrencySearch(c);
        assertThat(currencySearchService.listAllCurrencySearches()).hasSize(totalSearches + 1);
    }

    @Test
    public void testFindById() {
        assertThat(currencySearchService.listAllCurrencySearchesByUserId(userName).iterator().next().getUserId().equals(userName));
    }



}
