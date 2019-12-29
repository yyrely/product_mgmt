package com.chuncongcong.productmgmt.config.interceptor;

import static com.chuncongcong.productmgmt.model.constants.PublicConstants.USER_TOKEN_PRE;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chuncongcong.productmgmt.context.RequestContext;
import com.chuncongcong.productmgmt.exception.BaseErrorCode;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.po.UserInfoPo;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author HU
 * @date 2019/12/19 16:43
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (request.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name())) {
			return true;
		}
		String token = request.getHeader("token");
		String userJson = stringRedisTemplate.opsForValue().get(USER_TOKEN_PRE + token);
		if (StringUtils.isBlank(userJson)) {
			throw new ServiceException(BaseErrorCode.NOT_LOGIN);
		}
		UserInfoPo userInfoPo = objectMapper.readValue(userJson, UserInfoPo.class);
		RequestContext.setUserInfo(userInfoPo);
		return true;
	}
}
