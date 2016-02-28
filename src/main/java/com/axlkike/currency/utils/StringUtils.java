package com.axlkike.currency.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kikejf on 22/02/2016.
 */
public class StringUtils {
    //regular expression for email pattern
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * validate email
     * @param input
     * @return
     */
    public static boolean checkEmail(final String input) {
            Pattern  pattern = Pattern.compile(EMAIL_PATTERN);
            Matcher matcher = pattern.matcher(input);
            return matcher.matches();
    }

}
