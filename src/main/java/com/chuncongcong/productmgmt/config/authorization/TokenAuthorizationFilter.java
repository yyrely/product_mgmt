package com.chuncongcong.productmgmt.config.authorization;

import static com.chuncongcong.productmgmt.model.constants.PublicConstants.USER_TOKEN_PRE;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author Hu
 * @date 2019/3/15 9:56
 */

@Component
public class TokenAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    @Qualifier("userInfoServiceImpl")
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        // 从请求头中取出token
        String token = request.getHeader("token");
        if (!StringUtils.isEmpty(token)) {
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                // 从redis中获取用户名
                String mobile = redisTemplate.opsForValue().get(USER_TOKEN_PRE + token);
                if(!StringUtils.isEmpty(mobile)) {
                    // 从数据库中根据用户名获取用户
                    UserDetails userDetails = userDetailsService.loadUserByUsername(mobile);
                    if (userDetails != null) {
                        // 解析并设置认证信息（具体实现不清楚）
                        UsernamePasswordAuthenticationToken authentication =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }
}
