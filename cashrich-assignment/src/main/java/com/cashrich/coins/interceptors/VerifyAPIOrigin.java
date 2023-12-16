package com.cashrich.coins.interceptors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class VerifyAPIOrigin implements HandlerInterceptor {

    @Value("${origin.key}")
    private String originKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "content-type, x-requested-with");
        response.setHeader("Access-Control-Max-Age", "3600");

        String headerValue = request.getHeader("origin-api-key");
        if (headerValue == null || !headerValue.equals(originKey)) {
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            String message = "{ \"error\": \"Invalid origin key\" }";
            response.getWriter().write(message);
            return false;
        }
        return true;
    }
}
