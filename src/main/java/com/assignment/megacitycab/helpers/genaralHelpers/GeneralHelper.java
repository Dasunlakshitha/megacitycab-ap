package com.assignment.megacitycab.helpers.genaralHelpers;

import java.util.UUID;

public class GeneralHelper {
    public static final String FORM_ERRORS = "form_errors";
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String PAGE_TITLE = "PageTitle";
    public  static final String MSG = "msg";

    public static final String FORM_DATA = "form_data";


    public static String generateRandomTokenString(){
        return UUID.randomUUID().toString();
    }// END OF GENERATE RANDOM TOKEN STRING.

    public static boolean regexEmailValidationPattern(String email){
        // Set Pattern:
        String regex = "([a-zA-Z0-9]+(?:[._+-][a-zA-Z0-9]+)*)@([a-zA-Z0-9]+(?:[.-][a-zA-Z0-9]+)*[.][a-zA-Z]{2,})";

        if(email.matches(regex)) {
            return true;
        }
        return false;

    }// END OF REGULAR EXPRESSION EMAIL VALIDATION PATTERN.
}
