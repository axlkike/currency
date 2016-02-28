package com.axlkike.currency.service;

import com.axlkike.currency.CurrencyApplication;
import com.axlkike.currency.domain.CurrencySearch;
import com.axlkike.currency.services.IcurrencyLayerService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by kikejf on 28/02/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CurrencyApplication.class)
@IntegrationTest
public class CurrencyLayerServiceTest {

    @Inject
    IcurrencyLayerService currencyLayerService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testErrorNoData() {
        //pass to Currency Layer a currency that doesn't exist.
        assertThat( currencyLayerService.getCurrencySearch(
                   new CurrencySearch("testUser", null, "EUR", "XXX", false, null))).isNull();
    }

    @Test
    public void testHistoricalData() {
        assertThat( currencyLayerService.getCurrencySearch(
                new CurrencySearch("testUser", new DateTime(2015, 12, 13, 0, 0), "USD",
                        "EUR", true, null)).getExchange().toString()).isEqualTo("1.099250");
    }

    @Test
    public void testLiveData() {
        assertThat( currencyLayerService.getCurrencySearch(
                new CurrencySearch("testUser", null, "GBP", "EUR", false, null)).getExchange()).isNotZero();
    }

}

