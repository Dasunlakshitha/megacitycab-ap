package com.assignment.megacitycab.controllers;

import com.assignment.megacitycab.helpers.genaralHelpers.GeneralHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class AppController {

    private ModelAndView page;


    @GetMapping("/dashboard")
    public ModelAndView getDashboard(){
        this.page = new ModelAndView("dashboard");
        page.addObject(GeneralHelper.PAGE_TITLE, "Dashboard");
        return page;
    }// END OF GET DASHBOARD PAGE.
}
