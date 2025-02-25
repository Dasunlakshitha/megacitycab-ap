package com.assignment.megacitycab.helpers.validations;

import com.assignment.megacitycab.helpers.genaralHelpers.GeneralHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class SignInValidationHelper {
    @Setter
    private String username;
    @Setter
    private String password;
    private final HashMap<String, String> errors;

    public SignInValidationHelper(String username, String password) {
        this.username = username.toLowerCase();
        this.password = password;
        this.errors = new HashMap<>();

        checkForEmptyFields();
    }// END OF ALL ARGS CONSTRUCTOR.

    public void checkForEmptyFields(){

        if(this.getUsername().isEmpty()){
            errors.put("email", "Email field cannot be empty");
        }

        if(this.getPassword().isEmpty()){
            errors.put("password", "Password field cannot be empty");
        }


    }// END OF CHECK FOR EMPTY FIELDS.





}
