package com.axlkike.currency.bootstrap;

/**
 * Created by kikejf on 19/01/2016.
 */

import com.axlkike.currency.domain.CurrencySearch;
import com.axlkike.currency.domain.User;
import com.axlkike.currency.domain.UserRoleType;
import com.axlkike.currency.repositories.CurrencySearchRepository;
import com.axlkike.currency.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.math.BigDecimal;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    @Inject
    private CurrencySearchRepository currencySearchRepository;

    @Inject
    private UserRepository userRepository;

    private Logger log = Logger.getLogger(DataLoader.class);


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        User user1 = new User("user1", "$2a$10$w0HG6M9O/D6T.FwAYi2fcuZZi./iTlfdaQocb2LICIymLiwPyAunu",
                                "Fernando Lopez", "fernandolopez@currency.com",
                                new DateTime(2015, 10, 23, 9, 23), UserRoleType.USER.toString());
        userRepository.save(user1);

        log.info("Saved User " + user1.getUserId());

        User user2 = new User("user2", "$2a$10$JM7jmrF571sKcEBBNE08QejWoxe9kAsp9IpUxNohBYESsxzRAQYh6",
                                "Pedro Martinez", "pedromartinez@currency.com",
                                new DateTime(2012, 5, 3, 19, 2), UserRoleType.USER.toString());
        userRepository.save(user2);

        log.info("Saved User " + user2.getUserId());

        User admin = new User("admin", "$2a$10$XJQyGN5lzTGUUCo/.c34su1K5avM.7Apx1.WvnHDVN9ZX3G4sxH46",
                                "Juan Perez", "admin@currency.com",
                                new DateTime(2012, 5, 3, 19, 2), UserRoleType.ADMIN.toString());
        userRepository.save(admin);

        log.info("Saved User " + admin.getUserId());

        CurrencySearch search1  = new CurrencySearch("user1", new DateTime(2016, 1, 20, 19, 20), "USD",
                                                    "EUR", false, new BigDecimal("1.098300"));
        currencySearchRepository.save(search1);
        log.info("Saved search 1");

        CurrencySearch search2  = new CurrencySearch("user2", new DateTime(2015, 11, 15, 5, 45), "USD",
                                                    "EUR", true, new BigDecimal("1.102944"));
        currencySearchRepository.save(search2);
        log.info("Saved search 2");

        CurrencySearch search3  = new CurrencySearch("user2", new DateTime(2015, 7, 2, 14, 21), "EUR",
                                                    "GBP", true, new BigDecimal("0.779786"));
        currencySearchRepository.save(search3);
        log.info("Saved search 3");

        CurrencySearch search4  = new CurrencySearch("user1", new DateTime(2016, 1, 20, 19, 20), "USD",
                "EUR", false, new BigDecimal("1.098300"));
        currencySearchRepository.save(search4);
        log.info("Saved search 4");

        CurrencySearch search5  = new CurrencySearch("user2", new DateTime(2014, 11, 5, 5, 45), "USD",
                "EUR", true, new BigDecimal("1.600944"));
        currencySearchRepository.save(search5);
        log.info("Saved search 5");

        CurrencySearch search6  = new CurrencySearch("user2", new DateTime(2010, 3, 24, 1, 1), "EUR",
                "GBP", true, new BigDecimal("0.899786"));
        currencySearchRepository.save(search6);
        log.info("Saved search 6");

        CurrencySearch search7  = new CurrencySearch("user1", new DateTime(2016, 1, 4, 9, 0), "USD",
                "EUR", false, new BigDecimal("1.077300"));
        currencySearchRepository.save(search7);
        log.info("Saved search 7");

        CurrencySearch search8  = new CurrencySearch("user2", new DateTime(2005, 12, 6, 15, 40), "USD",
                "EUR", true, new BigDecimal("1.000944"));
        currencySearchRepository.save(search8);
        log.info("Saved search 8");

        CurrencySearch search9  = new CurrencySearch("user2", new DateTime(2015, 7, 2, 14, 21), "EUR",
                "GBP", true, new BigDecimal("0.819786"));
        currencySearchRepository.save(search9);
        log.info("Saved search 9");

        CurrencySearch search10  = new CurrencySearch("user1", new DateTime(2006, 8, 15, 19, 20), "USD",
                "EUR", false, new BigDecimal("1.098300"));
        currencySearchRepository.save(search10);
        log.info("Saved search 10");

        CurrencySearch search11 = new CurrencySearch("user2", new DateTime(2010, 1, 5, 5, 45), "USD",
                "EUR", true, new BigDecimal("1.567777"));
        currencySearchRepository.save(search11);
        log.info("Saved search 11");

        CurrencySearch search12  = new CurrencySearch("user2", new DateTime(2010, 3, 24, 1, 1), "EUR",
                "GBP", true, new BigDecimal("0.899786"));
        currencySearchRepository.save(search12);
        log.info("Saved search 12");

        CurrencySearch search13 = new CurrencySearch("user2", new DateTime(2010, 1, 5, 5, 45), "USD",
                "EUR", true, new BigDecimal("1.567777"));
        currencySearchRepository.save(search13);
        log.info("Saved search 13");

        CurrencySearch search14  = new CurrencySearch("user2", new DateTime(2011, 2, 24, 1, 1), "EUR",
                "USD", true, new BigDecimal("0.888786"));
        currencySearchRepository.save(search14);
        log.info("Saved search 14");

        CurrencySearch search15 = new CurrencySearch("user2", new DateTime(2009, 6, 5, 5, 45), "USD",
                "EUR", true, new BigDecimal("1.333777"));
        currencySearchRepository.save(search15);
        log.info("Saved search 15");

        CurrencySearch search16  = new CurrencySearch("user2", new DateTime(2015, 8, 24, 1, 1), "EUR",
                "USD", true, new BigDecimal("0.444444"));
        currencySearchRepository.save(search16);
        log.info("Saved search 16");
    }
}
