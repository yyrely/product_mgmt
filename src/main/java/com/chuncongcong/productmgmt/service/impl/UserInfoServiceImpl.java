package com.chuncongcong.productmgmt.service.impl;

import static com.chuncongcong.productmgmt.model.constants.PublicConstants.USER_MOBILE_PRE;
import static com.chuncongcong.productmgmt.model.constants.PublicConstants.USER_TOKEN_PRE;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chuncongcong.productmgmt.dao.UserInfoDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.po.UserInfoPo;
import com.chuncongcong.productmgmt.model.vo.UserInfoVo;
import com.chuncongcong.productmgmt.model.vo.WxLoginResponse;
import com.chuncongcong.productmgmt.model.vo.WxLoginVo;
import com.chuncongcong.productmgmt.service.UserInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author HU
 * @date 2019/12/19 14:22
 */

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Value("${app.id}")
    private String appId;

    @Value("${app.secret}")
    private String appSecret;

    @Autowired
	private RestTemplate restTemplate;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public UserInfoVo login(UserInfoVo userInfoVo) throws Exception {
        UserInfoPo query = new UserInfoPo();
        query.setMobile(userInfoVo.getMobile());
        UserInfoPo userInfoPo = userInfoDao.selectOne(query);
        if (userInfoPo == null) {
            throw new ServiceException("用户不存在");
        }
        if (!userInfoPo.getPassword().equals(userInfoVo.getPassword())) {
            throw new ServiceException("用户名或密码不正确");
        }
		String existToken = stringRedisTemplate.opsForValue().get(USER_MOBILE_PRE + userInfoPo.getMobile());
        if(StringUtils.isNotEmpty(existToken)) {
			stringRedisTemplate.expire(USER_MOBILE_PRE + userInfoPo.getMobile(), 0, TimeUnit.SECONDS);
			stringRedisTemplate.expire(USER_TOKEN_PRE + existToken, 0, TimeUnit.SECONDS);
		}

		String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(USER_MOBILE_PRE + userInfoPo.getMobile(), token);
        stringRedisTemplate.opsForValue().set(USER_TOKEN_PRE + token, objectMapper.writeValueAsString(userInfoPo));
        userInfoVo.setPassword(null);
        userInfoVo.setToken(token);
        userInfoVo.setUsername(userInfoPo.getUsername());
        return userInfoVo;
    }

    @Override
    public WxLoginVo wxLogin(String code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret
            + "&js_code=" + code + "&grant_type=authorization_code";
		ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
		String responseJson = responseEntity.getBody();
		WxLoginResponse wxLoginResponse = objectMapper.readValue(responseJson, WxLoginResponse.class);
		if(wxLoginResponse.getErrcode() != null) {
			throw new ServiceException(wxLoginResponse.getErrcode(), ServiceException.DEFAULT_HTTP_CODE, wxLoginResponse.getErrmsg());
		}
		WxLoginVo wxLoginVo = new WxLoginVo();
		wxLoginVo.setOpenId(wxLoginResponse.getOpenid());
		wxLoginVo.setToken(wxLoginResponse.getSession_key());
		return wxLoginVo;
	}
}
