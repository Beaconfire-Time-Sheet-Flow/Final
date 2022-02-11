package com.example.demo.security;

import com.example.demo.constant.JwtConstant;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        HttpServletRequest req = (HttpServletRequest) request;
        String token = CookieUtil.getValue(request, JwtConstant.JWT_COOKIE_NAME);
//        String authorization = request.getHeader("Authorization");
//        if(authorization!=null){
//            String token = authorization.substring(7);

            if (token!=null) {
                System.out.println("+++++++++JwtFilter++++++++");
                System.out.println(token);
                String userName = JwtUtil.getSubjectFromJwt(token);
                System.out.println(userName);

                if (userName!=null) {
                    request.setAttribute("userName", userName);

                    filterChain.doFilter(request, response);
                } else {
                    String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
                    response.sendRedirect(authLoginUrl + "?redirect=" + request.getRequestURL());
                }
            } else {
                String authLoginUrl = getFilterConfig().getInitParameter("authLoginUrl");
                response.sendRedirect(authLoginUrl + "?redirect=" + request.getRequestURL());
            }
        }




}
