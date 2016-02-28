package com.axlkike.currency.services;
/**
 * Created by kikejf on 22/02/2016.
 */
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Locale;

/**
 * Created by kikejf on 22/02/2016.
 */
@Component
public class MessageByLocaleServiceImpl implements ImessageByLocaleService {

    @Inject
    private MessageSource messageSource;

    @Override
    public String getMessage(String id) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(id,null,locale);
    }
}
