package com.example.demo.controller;

import com.example.demo.constant.JwtConstant;
import com.example.demo.util.CookieUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/filter")
public class SucController {
    @GetMapping("/suc")
    public String suc(Model model, HttpServletRequest request){
        String token = CookieUtil.getValue(request, JwtConstant.JWT_COOKIE_NAME);
        model.addAttribute("token",token);
        return "success";
    }
}
