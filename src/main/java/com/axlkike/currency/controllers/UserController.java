package com.axlkike.currency.controllers;

import com.axlkike.currency.domain.User;
import com.axlkike.currency.services.IcurrencyLayerService;
import com.axlkike.currency.services.ImessageByLocaleService;
import com.axlkike.currency.services.IuserService;
import com.axlkike.currency.utils.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * Created by kikejf on 28/02/2016.
 */
@Controller
public class UserController {

    @Inject
    private IuserService userService;

    @Inject
    private ImessageByLocaleService messageService;

    /**
     * call to register a user
     * @param model
     * @return
     */
    @RequestMapping("/userform")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "userform";
    }



    /**
     * after submit save user form
     * @param user
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "saveuser")
    public String saveUser(@Valid User user, BindingResult bindingResult) {

        //retype password
        if (!("" + user.getRetypePassword()).equals(user.getPassword())) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "retypePassword",messageService.getMessage("error_retype")));
        }

        //email is not valid. Add error.
        if (!StringUtils.checkEmail(user.getEmail())) {
            bindingResult.addError(new FieldError(bindingResult.getObjectName(),
                    "email",messageService.getMessage("error_email")));
        }

        if (!bindingResult.hasErrors()) {
            userService.saveUser(user);
            return "redirect:/currencysearch";
        }

        return "userform";
    }
}
