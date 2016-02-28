package com.axlkike.currency.controllers;

/**
 * Created by kikejf on 19/01/2016.
 */
import com.axlkike.currency.domain.CurrencySearch;
import com.axlkike.currency.domain.User;
import com.axlkike.currency.services.IcurrencyLayerService;
import com.axlkike.currency.services.ImessageByLocaleService;
import com.axlkike.currency.services.IcurrencySearchService;
import com.axlkike.currency.services.IuserService;
import com.axlkike.currency.utils.StringUtils;
import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class CurrencySearchController {
    @Inject
    private IcurrencySearchService currencySearchService;

    @Inject
    private ImessageByLocaleService messageService;

    @Inject
    private IcurrencyLayerService currencyLayerService;


    @RequestMapping(value = "/currencysearch")
    public String listCurrency(Model model){
         org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CurrencySearch currSearchForm = new CurrencySearch();
        currSearchForm.setUserId(user.getUsername());
        model.addAttribute("currencysearchform", currSearchForm);
        model.addAttribute("currencysearchlist", currencySearchService.listAllCurrencySearchesByUserId(user.getUsername()));
        return "currencysearch";
    }


    /**
     * currency search
     * @param model
     * @return
     */
    @RequestMapping(value = "/allcurrencysearch")
    public String listAll(Model model){
        org.springframework.security.core.userdetails.User user =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CurrencySearch currSearchForm = new CurrencySearch();
        currSearchForm.setUserId(user.getUsername());
        model.addAttribute("currencysearchform", currSearchForm);
        model.addAttribute("currencysearchlist", currencySearchService.listAllCurrencySearches());
        return "currencysearch";
    }



    /**
     * after submit save currencySearch form
     * @param currencysearchform
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/savecurrency")
    public String saveCurrency(Model model,
                               @ModelAttribute("currencysearchform") CurrencySearch currencysearchform,
                               BindingResult bindingResult) {
        if (currencysearchform.getCurrFrom().equals(currencysearchform.getCurrTo())) {
            //not allowed i.e. usd to usd
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "searchDate",messageService.getMessage("error_selectcurrency")));
        } else {
            CurrencySearch currResult = currencyLayerService.getCurrencySearch(currencysearchform);
            if (currResult != null) {
                currencySearchService.saveCurrencySearch(currResult);
            } else {
                //error consulting external currency provider
                bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                        "searchDate",messageService.getMessage("error_currency")));
            }
        }

        model.addAttribute("currencysearchlist", currencySearchService.listAllCurrencySearchesByUserId(currencysearchform.getUserId()));
        return "currencysearch";

    }

}