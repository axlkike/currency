package com.axlkike.currency.controllers;


import com.axlkike.currency.configuration.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kikejf on 28/02/2016.
 */
@Controller
public class ClearCacheController {

    @CacheEvict(value = CacheConfig.LONG_CACHE, allEntries = true)
    @RequestMapping(value = "/clearcache", method = RequestMethod.GET)
    public String clearCache() {
        return "clearcache";
    }
}