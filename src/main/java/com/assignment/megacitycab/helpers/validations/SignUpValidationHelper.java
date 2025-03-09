package com.assignment.megacitycab.helpers.validations;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class SignUpValidationHelper {
    private String userName;
    private String password;
    private String confirmPassword;
    private String userRole;

    private final HashMap<String, String> errors;

    public SignUpValidationHelper(String userName, String password, String confirm, String userRole) {
        this.userName = userName;
        this.password = password;
        this.confirmPassword = confirm;
        this.userRole = userRole;

        errors = new HashMap<>();

    }
}
