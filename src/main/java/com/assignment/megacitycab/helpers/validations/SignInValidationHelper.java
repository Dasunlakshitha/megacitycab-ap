package com.assignment.megacitycab.helpers.validations;

import com.assignment.megacitycab.helpers.genaralHelpers.GeneralHelper;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
public class SignInValidationHelper {
    @Setter
    private String email;
    @Setter
    private String password;
    private final HashMap<String, String> errors;

    public SignInValidationHelper(String username, String password) {
        this.email = username.toLowerCase();
        this.password = password;
        this.errors = new HashMap<>();

        checkForEmptyFields();
        checkIfEmailIsValid();
    }// END OF ALL ARGS CONSTRUCTOR.

    public void checkForEmptyFields(){

        if(this.getEmail().isEmpty()){
            errors.put("email", "Email field cannot be empty");
        }

        if(this.getPassword().isEmpty()){
            errors.put("password", "Password field cannot be empty");
        }


    }// END OF CHECK FOR EMPTY FIELDS.

    public void checkIfEmailIsValid(){
        if(!GeneralHelper.regexEmailValidationPattern(this.getEmail())){
            errors.put("email", "Please enter a valid email address");
        }

    }// END OP CHECK IF EMAIL IS VALID.



}
