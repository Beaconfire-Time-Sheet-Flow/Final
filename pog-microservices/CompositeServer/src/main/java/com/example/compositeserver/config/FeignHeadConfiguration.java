package com.example.compositeserver.config;

import feign.RequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
public class FeignHeadConfiguration {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;
    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFormEncoder(new SpringEncoder(messageConverters));
    }
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attrs != null) {
                HttpServletRequest request = attrs.getRequest();
                Cookie[] cookies = request.getCookies();
                if (cookies != null && cookies.length > 0) {
                    for (Cookie cookie : cookies) {
                        requestTemplate.header(cookie.getName(), cookie.getValue());
                    }
                } else {
                    logger.warn("FeignHeadConfiguration", "Get Cookie failed");
                }
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        String name = headerNames.nextElement();
                        String value = request.getHeader(name);
                        if ("jsessionid".equalsIgnoreCase(name)) {
                            logger.debug("Add custom header key:" + name + ",value:" + value);
                            requestTemplate.header(name, value);
                        } else {
                            logger.debug("FeignHeadConfiguration", "not custom header key:" + name + ",value:" + value + "no need to add!");
                        }
                    }
                } else {
                    logger.warn("FeignHeadConfiguration", "Get header failed!");
                }
            }
        };
    }
}
