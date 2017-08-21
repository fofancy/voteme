package com.fofancy.voteme.web.account.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by User on 12.07.2017.
 */
@Controller
public class AuthRoutingController {

    @RequestMapping("/account/login")
    public String getLoginPage() {
        return "account/login";
    }

    @RequestMapping("/account/personal-page")
    public String getPersonalPage() {
        return "account/personal-page";
    }

    @RequestMapping("/account/personal-page/content")
    public String getPersonalPageContent() {
        return "account/personal-page-content";
    }

    @RequestMapping("/account/admin")
    public String getAdminPage() {
        return "account/admin";
    }

    @RequestMapping("/account/admin/content")
    public String getAdminPageContent() {
        return "account/admin-page-content";
    }
}
