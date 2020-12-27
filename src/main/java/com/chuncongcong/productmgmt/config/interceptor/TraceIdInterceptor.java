package com.chuncongcong.productmgmt.config.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author HU
 * @date 2020/12/27 16:01
 */

@Component
public class TraceIdInterceptor implements HandlerInterceptor {

	static final private String TRACE_ID = "traceId";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		MDC.put(TRACE_ID, UUID.randomUUID().toString());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		MDC.clear();
	}
}
