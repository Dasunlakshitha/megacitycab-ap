package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.helpers.genaralHelpers.GeneralHelper;
import com.assignment.megacitycab.helpers.validations.SignUpValidationHelper;
import com.assignment.megacitycab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Random;

@Controller
public class SigUpController {
    @Autowired
    private UserService userService;

    private ModelAndView page;

    @PostMapping("/signup")
    public ModelAndView getSignUpPage(@RequestParam(value = "role", required = false) String role) {
        this.page = new ModelAndView("signup");
        this.page.addObject("title", "Sign Up");
        this.page.addObject("role", role);
        return this.page;
    }

    @PostMapping("/sign-up")
    public ModelAndView signUp(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirm") String confirm,
                               @RequestParam("user_role") String user_role) {

        //TODO: VALIDATE FORM DATA:
        SignUpValidationHelper validate = new SignUpValidationHelper(username, password, confirm, user_role);

        if (!validate.getErrors().isEmpty()) {
            this.page = new ModelAndView("sign-up");
            this.page.addObject(GeneralHelper.FORM_ERRORS, validate.getErrors());
            this.page.addObject(GeneralHelper.FORM_DATA, validate);
            return this.page;
        }// END OF VALIDATE FORM DATA IF BLOCK.

        // TODO: PROCESS FORM REGISTRATION:
        String token = GeneralHelper.generateRandomTokenString();
        Random random = new Random();
        int code = random.nextInt(123) * 100;

        // HASH PASSWORD:
        String hashedPassword = BCrypt.hashpw(validate.getPassword(), BCrypt.gensalt());

        // INSERT DATA / REGISTER USER:
        int result =
                userService.registerUser(validate.getUserName(), hashedPassword, validate.getUserRole(), token, code);

        if (result != 1) {
            this.page = new ModelAndView("sign-up");
            this.page.addObject(GeneralHelper.ERROR, "Error Registering user");
            return this.page;
        }

        // TODO: SEND EMAIL NOTIFICATION:
/*        String emailBody = MessageHelper.verifyAccountEmailBody(token, String.valueOf(code));
        MailMessengerHelper.htmlEmailMessenger("no-reply-verify@example.com", validate.getEmail(), "verify-Account", emailBody);*/

        // TODO: RE-ROUTE TO SUCCESS PAGE.
        if (user_role.equals("ADMIN")) {
            this.page = new ModelAndView("/app/adminDashboard");
        }
        if (user_role.equals("CUSTOMER")) {
            this.page = new ModelAndView("/app/customerDashboard");
        }
        if (user_role.equals("DRIVER")) {
            this.page = new ModelAndView("/app/driverDashboard");
        }
        //this.page = new ModelAndView("signup");
        this.page.addObject(GeneralHelper.SUCCESS, "Registration Successful,");
        return this.page;
    }// END OF SIGN UP POST METHO


}
