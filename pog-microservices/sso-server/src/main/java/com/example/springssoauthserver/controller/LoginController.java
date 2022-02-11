package com.example.springssoauthserver.controller;

import com.example.springssoauthserver.constant.JwtConstant;
import com.example.springssoauthserver.entity.Account;
import com.example.springssoauthserver.security.CookieUtil;
import com.example.springssoauthserver.security.JwtUtil;
import com.example.springssoauthserver.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("redirect") String redirect, HttpServletRequest request, HttpServletResponse res,
                        @RequestParam String username, @RequestParam String password, Model model) {
        List<Account> accounts = accountService.checkLoginRepo(username,password);
        if (username==null || password==null || accounts == null) {
            model.addAttribute("credentialError", "Invalid username or password");
            return "login";
        }

        String jwt = JwtUtil.generateToken(username, JwtConstant.JWT_VALID_DURATION, accounts.get(0).getId());
        //Setting maxAge to -1 will preserve it until the browser is closed.
        CookieUtil.create(res, JwtConstant.JWT_COOKIE_NAME, jwt, false, -1, "localhost");

//        model.addAttribute("token", jwt);
        return "redirect:" + "http://localhost:3000/summary";
    }


    @GetMapping("/logout")
    public String logout(HttpServletResponse res, @RequestParam("redirect") String redirect) {
        CookieUtil.clear(res, JwtConstant.JWT_COOKIE_NAME, "localhost");
        return "redirect:" + redirect;
    }
}
