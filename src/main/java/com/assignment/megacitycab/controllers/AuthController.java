package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.helpers.genaralHelpers.GeneralHelper;
import com.assignment.megacitycab.helpers.validations.SignInValidationHelper;
import com.assignment.megacitycab.model.User;
import com.assignment.megacitycab.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/*
package com.assignment.megacitycab.controllers;


import com.assignment.megacitycab.model.AppUser;
import com.assignment.megacitycab.repository.UserRepository;
import com.assignment.megacitycab.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;


@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping(value = "/api/register", headers = "application/json")
    public AppUser registerUser(@RequestBody AppUser user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userService.registerUser(user);
        } catch (DataIntegrityViolationException e) {  // Handle unique constraint violation
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists"); // Or return a custom error response
        }
    }

    @PostMapping("/api/test")
    public String testEndpoint(@RequestBody String data) {
        System.out.println("Received data: " + data);
        return "Test successful";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // This maps to /WEB-INF/views/login.jsp
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";  // This maps to /WEB-INF/views/login.jsp
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";  // This maps to /WEB-INF/views/login.jsp
    }
}
*/
@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    private ModelAndView page;


    @GetMapping("/signin")
    public ModelAndView getSignIn() {
        this.page = new ModelAndView("signin");
        this.page.addObject("PageTitle", "Sign In");
        return this.page;
    }// END OF GET SIGN IN PAGE GET METHOD.


    @PostMapping("/sign-in")
    public String authenticateUser(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   Model model, HttpSession session) {

        // TODO: VALIDATE FORM DATE / INPUT FIELDS:
        SignInValidationHelper validate = new SignInValidationHelper(username, password);
        if (!validate.getErrors().isEmpty()) {
            model.addAttribute(GeneralHelper.FORM_ERRORS, validate.getErrors());
            model.addAttribute(GeneralHelper.FORM_DATA, validate);
            return "sign-in";
        }// END OF VALIDATE FORM DATA / INPUT FIELDS IF BLOCK.

        // TODO: CHECK IF THE EMAIL EXISTS:
        String doesUserExist = userService.doesUserExist(validate.getUsername());

        if (doesUserExist == null || doesUserExist.isEmpty()) {
            model.addAttribute("msg", "There is no account linked to this email");
            return "error";
        }// END OF CHECK IF EMAIL EXISTS IF BLOCK.

        // TODO: CHECK IF ACCOUNT IS VERIFIED:
       // int verified = userService.isAccountVerified(validate.getUsername());

       /* if (verified == 0) {
            model.addAttribute("msg", "Your Account is not yet verified, please check your email and verify account");
            return "error";
        }// END OF CHECK IF ACCOUNT IS VERIFIED.*/

        // TODO: PROCEED TO AUTHENTICATE USER:
        String dbPassword = userService.getDbPasswordByUsername(validate.getUsername());

        if (!BCrypt.checkpw(validate.getPassword(), dbPassword)) {
            model.addAttribute(GeneralHelper.ERROR, "Incorrect Username or Password");
            return "signin";
        }// VALIDATE / AUTHENTICATE USER IF BLOCK.


        // TODO: INIT USER OBJECT AND SET SESSION:
        User user = userService.getUserDetailsByUsername(validate.getUsername());

        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/app/dashboard";
            //return "redirect:/index";
        }

        return "redirect:/error";
    }// END OF AUTHENTICATE USER METHOD.


    @PostMapping("/sign-out")
    public String signOut(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        System.out.println("Sign-out: Session " + session.getId() + " has been invalidated");
        redirectAttributes.addFlashAttribute(GeneralHelper.SUCCESS, "You have successfully signed out");
        return "redirect:/sign-in";
    }// END OF SIGN OUT POST METHOD.


    @GetMapping("/verify-account")
    public ModelAndView verifyAccount(@RequestParam("token") String token, @RequestParam("code") String code) {

        // TODO: VERIFY TOKEN AND CODE:
        List<String> tokenAndCode = userService.checkTokenAndCode(token, Integer.parseInt(code));

        if (tokenAndCode == null) {
            this.page = new ModelAndView("error");
            this.page.addObject("msg", "Something went wrong, contact Administrator");
            return this.page;

        }// END OF CHECK TOKEN AND CODE IF BLOCK.

        // TODO: VERIFY ACCOUNT:
        int result = userService.verifyAccount(token, Integer.parseInt(code));

        if (result != 1) {
            this.page = new ModelAndView("error");
            this.page.addObject("msg", "Something went wrong, contact Administrator");
            return this.page;
        }// END OF CHECK FOR RESULT SET IF BLOCK.

        // TODO: REDIRECT TO SUCCESS PAGE:
        this.page = new ModelAndView("success");
        this.page.addObject("msg", "Verification Successful");
        return this.page;

    }// END OF VERIFY ACCOUNT GET METHOD.

}// END OF AUTH CONTROLLER CLASS.
