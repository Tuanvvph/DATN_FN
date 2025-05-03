package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @RequestMapping(value = {"/account"}, method = RequestMethod.GET)
    public String account() {
        return "user/account.html";
    }

    @RequestMapping(value = {"/forgot-pass"}, method = RequestMethod.GET)
    public String forgotPass() {
        return "user/forgot-pass.html";
    }

    @RequestMapping(value = {"/checkout"}, method = RequestMethod.GET)
    public String checkout() {
        return "user/checkout.html";
    }

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home() {
        return "redirect:/index";
    }

    @RequestMapping(value = {"/index"}, method = RequestMethod.GET)
    public String index() {
        return "user/index.html";
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login() {
        return "user/login.html";
    }

    @RequestMapping(value = {"/payment"}, method = RequestMethod.GET)
    public String payment() {
        return "user/payment.html";
    }

    @RequestMapping(value = {"/product-detail"}, method = RequestMethod.GET)
    public String product() {
        return "user/product-detail.html";
    }

    @RequestMapping(value = {"/gioithieu"}, method = RequestMethod.GET)
    public String gioithieu() {
        return "user/gioithieu.html";
    }

    @RequestMapping(value = {"/lienhe"}, method = RequestMethod.GET)
    public String lienhe() {
        return "user/lienhe.html";
    }


}
