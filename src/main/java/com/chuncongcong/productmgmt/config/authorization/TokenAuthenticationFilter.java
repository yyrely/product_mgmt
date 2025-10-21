package com.chuncongcong.productmgmt.config.authorization;

import com.chuncongcong.productmgmt.model.po.UserInfoPo;
import com.chuncongcong.productmgmt.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author Hu
 * @date 2019/3/14 17:46
 */

@Slf4j
public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper;
    private AuthenticationManager authenticationManager;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.objectMapper = objectMapper;
        super.setFilterProcessesUrl("/api/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            //从body中取出,参数是在请求体中使用json形式传入的
            UserInfoPo users = objectMapper.readValue(request.getInputStream(), UserInfoPo.class);
            log.info("body userInfo:{}",objectMapper.writeValueAsString(users));
            //模仿UsernamePasswordAuthenticationFilter的方式，将用户名密码进行比较
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(users.getMobile(), users.getPassword()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthUser user = (AuthUser) authResult.getPrincipal();
        String token = JwtUtil.generateToken(user.getUsername());

        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\n" +
                "    \"code\": 1,\n" +
                "    \"data\": {\n" +
                "        \"username\": \""+user.getUsername()+"\",\n" +
                "        \"token\": \""+token+"\",\n" +
                "        \"roles\": "+objectMapper.writeValueAsString(user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))+"\n" +
                "    }\n" +
                "}");
        response.flushBuffer();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(500);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print("{\"code\": 500, \n \"msg\":\"用户名或密码不正确，登陆失败\"}");
        response.flushBuffer();
    }
}
