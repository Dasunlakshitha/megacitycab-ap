package com.assignment.megacitycab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView getIndexPage = new ModelAndView("index");
        getIndexPage.addObject("PageTitle", "Home");
        return getIndexPage;
    }
    @GetMapping("/dashboard")
    public ModelAndView getdashboard(){
        ModelAndView getDashboardPage = new ModelAndView("dashboard");
        getDashboardPage.addObject("PageTitle", "Home");
        return getDashboardPage;
    }
}
