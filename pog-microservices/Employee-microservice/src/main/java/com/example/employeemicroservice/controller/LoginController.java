package com.example.employeemicroservice.controller;

import com.example.employeemicroservice.constant.JwtConstant;
import com.example.employeemicroservice.entity.Account;
import com.example.employeemicroservice.service.ProfileService;
import com.example.employeemicroservice.util.CookieUtil;
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
    private ProfileService accountService;

    @GetMapping("/")
    public String initMethod(Model model){
        model.addAttribute("user", new Account());
        return "home";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,
                        @ModelAttribute("user")@Valid Account account,
                        Model model){
        List<Account> list = accountService.checkLogin(account.getUserName(), account.getPassword());
        if(list!=null){
            String token = CookieUtil.getValue(request, JwtConstant.JWT_COOKIE_NAME);
            model.addAttribute("token",token);
            return "forward:/"+"localhost:3000/summary";
        }else {
            model.addAttribute("exception", "wrong username or pwd");
            model.addAttribute("url", request.getRequestURL());
            return "errorPage";
        }
    }
}
