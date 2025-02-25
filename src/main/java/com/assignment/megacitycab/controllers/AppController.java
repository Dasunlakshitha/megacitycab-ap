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


    @GetMapping("/adminDashboard")
    public ModelAndView getAdminDashboard(){
        this.page = new ModelAndView("admin-dashboard");
        page.addObject(GeneralHelper.PAGE_TITLE, "Dashboard");
        return page;
    }// END OF GET DASHBOARD PAGE.
    @GetMapping("/driverDashboard")
    public ModelAndView getDriverDashboard(){
        this.page = new ModelAndView("driver-dashboard");
        page.addObject(GeneralHelper.PAGE_TITLE, "Dashboard");
        return page;
    }// END OF GET DASHBOARD PAGE.
    @GetMapping("/customerDashboard")
    public ModelAndView getCustomerDashboard(){
        this.page = new ModelAndView("customer-dashboard");
        page.addObject(GeneralHelper.PAGE_TITLE, "Dashboard");
        return page;
    }// END OF GET DASHBOARD PAGE.
}
