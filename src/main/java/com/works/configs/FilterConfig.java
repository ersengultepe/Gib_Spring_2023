package com.works.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");

        String url = req.getRequestURI();
        String[] freeUrl = {"/customer/login", "/customer/register"};
        boolean loginStatus = true;
        for( String item : freeUrl ) {
            if (item.equals(url)) {
                loginStatus = false;
                break;
            }
        }

        if (loginStatus) {
            // session control
            boolean sessionStatus = req.getSession().getAttribute("user") == null;
            if (sessionStatus) {
                // Session Empty!
                PrintWriter printWriter = res.getWriter();
                String st = "{ \"status\": true, \"result\": \"Plase Login!\" }";
                printWriter.print(st);
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            }else {
                chain.doFilter(req, res);
            }
        }else {
            chain.doFilter(req, res);
        }

    }

}
