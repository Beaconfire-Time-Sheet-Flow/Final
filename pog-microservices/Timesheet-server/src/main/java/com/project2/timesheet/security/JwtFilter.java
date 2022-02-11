package com.project2.timesheet.security;

import com.project2.timesheet.constant.JwtConstant;
import com.project2.timesheet.util.CookieUtil;
import com.project2.timesheet.util.JwtUtil;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = CookieUtil.getValue(request, JwtConstant.JWT_COOKIE_NAME);
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
