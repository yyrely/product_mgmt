package com.chuncongcong.productmgmt.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HU
 * @date 2021/7/19 15:36
 */

@Slf4j
@Component
public class LogFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
		log.info("[url : {}], [params : {}]", httpServletRequest.getRequestURL(), httpServletRequest.getQueryString());
		filterChain.doFilter(servletRequest, servletResponse);
	}
}
