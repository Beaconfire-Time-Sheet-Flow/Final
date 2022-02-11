package com.example.demo.controller;

import com.example.demo.constant.JwtConstant;
import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;
import com.example.demo.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/")
    public String initMethod(Model model){
        model.addAttribute("user", new Account());
        return "home";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @ModelAttribute("user")@Valid Account account,
                        Model model){
        List<Account> list = accountService.checkLoginRepo(account.getUsername(), account.getPassword());
        if(list!=null){
            String token = CookieUtil.getValue(request, JwtConstant.JWT_COOKIE_NAME);
            model.addAttribute("token",token);
            return "forward:/"+"suc";
        }else {
            model.addAttribute("exception", "wrong username or pwd");
            model.addAttribute("url", request.getRequestURL());
            return "errorPage";
        }
    }
}
