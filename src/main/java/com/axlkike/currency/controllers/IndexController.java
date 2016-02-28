package com.axlkike.currency.controllers;

/**
 * Created by kikejf on 19/01/2016.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    String index(){
        return "index";
    }
}

