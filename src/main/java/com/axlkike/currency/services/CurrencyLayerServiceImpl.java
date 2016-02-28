package com.axlkike.currency.services;
/**
 * Created by kikejf on 22/02/2016.
 */
import com.axlkike.currency.configuration.CacheConfig;
import com.axlkike.currency.domain.CurrencySearch;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class CurrencyLayerServiceImpl implements IcurrencyLayerService {

	// essential URL structure is built using constants 
	public static final String ACCESS_KEY = "?access_key=6c80fcb06f727169a1065b319fe548ee";
	public static final String BASE_URL = "http://apilayer.net/api/";
	public static final String ENDPOINT_LIVE = "live";
	public static final String ENDPOINT_HISTORICAL = "historical";
	public static final String CURRENCIES = "&currencies=";
    public static final String DATE_PARAM = "&date=";

    public static final String RESULT_SUCCESS = "success";
    public static final String RESULT_DATE = "timestamp";
    public static final String RESULT_QUOTES = "quotes";
    public static final String RESULT_CURR_PREFIX = "USD";

	@Override
    @Cacheable(CacheConfig.LONG_CACHE)
	public CurrencySearch getCurrencySearch(CurrencySearch currencySearch) {

		String currencies = null;
		
		if (currencySearch.getCurrFrom() == null || currencySearch.getCurrTo() == null) {
			//not possible. Return null
			return null;
		} else {
			currencies = CURRENCIES + currencySearch.getCurrFrom() + "," + currencySearch.getCurrTo();
		}
		
		//default endpoint and historical date
		String endPoint = ENDPOINT_LIVE;
		String histDate = "";

		if (currencySearch.getHistorical()) {
			endPoint = ENDPOINT_HISTORICAL;
            DateTimeFormatter dtfOut = DateTimeFormat.forPattern("yyyy-MM-dd");
            histDate = DATE_PARAM + dtfOut.print(currencySearch.getSearchDate());
		}
		
		String url = BASE_URL + endPoint + ACCESS_KEY + histDate + currencies;

        RestTemplate restTemplate = new RestTemplate();

		try {

            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());

            if (!root.get(RESULT_SUCCESS).asBoolean()) {
                return null;
            }

			//seconds to milliseconds and convert to joda
			currencySearch.setSearchDate(new DateTime(root.get(RESULT_DATE).asLong()*1000));

            BigDecimal srcCurr = new BigDecimal(root.get(RESULT_QUOTES).get(RESULT_CURR_PREFIX +
                                                currencySearch.getCurrFrom()).asText());
            BigDecimal dstCurr = new BigDecimal(root.get(RESULT_QUOTES).get(RESULT_CURR_PREFIX +
                                                currencySearch.getCurrTo()).asText());

            currencySearch.setExchange(srcCurr.divide(dstCurr, 6, BigDecimal.ROUND_FLOOR));

        } catch (Exception e) {
			// do nothing
            return null;
		}
		
		return currencySearch;
	}

}
