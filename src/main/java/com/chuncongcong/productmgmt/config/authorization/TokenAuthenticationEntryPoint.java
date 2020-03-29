package com.chuncongcong.productmgmt.config.authorization;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Hu
 * @date 2019/3/15 17:53
 */

@Slf4j
@Component
public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("security not login handler");
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\"code\":\"401\", \n \"msg\":\"not login\"}");
        response.flushBuffer();
    }
}
