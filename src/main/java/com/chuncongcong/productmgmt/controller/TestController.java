package com.chuncongcong.productmgmt.controller;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.exception.ServiceException;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author HU
 * @date 2019/12/18 17:16
 */

@Slf4j
@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*",maxAge = 3600)
public class TestController {

	private static final String FILE_PATH = "/data/mobile/mobile.txt";

	private String token;

	private String refreshToken;

	@Autowired
	private StringEncryptor stringEncryptor;

	@Lazy
	@Autowired
	private TestController testController;

	@Autowired
	private StringRedisTemplate redisTemplate;


	@GetMapping("/token")
	public Object token() {
		Map<String, String> map = new HashMap<>();
		String token = UUID.randomUUID().toString();
		this.token = token;
		String refreshToken = UUID.randomUUID().toString();
		this.refreshToken = refreshToken;
		map.put("token", token);
		map.put("refresh_token", refreshToken);
		return map;
	}

	@GetMapping("/refresh/token")
	public Object refreshToken(String refreshToken) {
		if(!refreshToken.equals(this.refreshToken)) {
			throw new ServiceException("refresh_token 已过期请检查");
		}
		Map<String, String> map = new HashMap<>();
		String token = UUID.randomUUID().toString();
		this.token = token;
		String newRefreshToken = UUID.randomUUID().toString();
		this.refreshToken = newRefreshToken;
		map.put("token", token);
		map.put("refresh_token", newRefreshToken);
		return map;
	}

	@GetMapping("/getInfo")
	public Object getInfo(@RequestHeader("token") String token, String test) {
		if(!token.equals(this.token)) {
			throw new ServiceException("token 已过期请检查");
		}
		return test;
	}

	@GetMapping("/getMobile")
	public Object getMobile(@RequestParam(value = "size", defaultValue = "100") Integer size) {
		redisTemplate.opsForValue().set("mobile:flag", "0");
		testController.func(size);
		return null;
	}

	@GetMapping("/mobile/download")
	public Object downloadMobile(HttpServletResponse response) throws Exception {
		String mobileFlag = redisTemplate.opsForValue().get("mobile:flag");
		if(StringUtils.isEmpty(mobileFlag) || "0".equals(mobileFlag) ) {
			throw new ServiceException("文件还在生成中，请稍后再试");
		}

		InputStream is = new BufferedInputStream(new FileInputStream(FILE_PATH));
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition",
				"attachment; filename=" + URLEncoder.encode("mobile.txt", "UTF-8"));
		ServletOutputStream os = response.getOutputStream();
		IoUtil.copy(is, os);
		is.close();
		return null;
	}

	@Async
	public void func(Integer size) {
		Map<String, Object> getMobileParam = new HashMap<>();
		getMobileParam.put("groupId", "17e2bc48192047be835f69c47323259a");
		getMobileParam.put("num", size);
		getMobileParam.put("keywordScope", "suffix");
		getMobileParam.put("appId", "wxd165037244dc5f53");
		getMobileParam.put("appCode", "zjyx");

		String mobileResult = HttpUtil.post("https://wapzj.189.cn/cloudsale/mobile/api/1.0/product/randomphone/list", JSONUtil.toJsonStr(getMobileParam));
		JSONObject jsonObject = JSONUtil.parseObj(mobileResult);
		JSONObject result = jsonObject.getJSONObject("result");
		JSONArray phones = result.getJSONArray("phones");
		log.info("mobile size:{}", phones.size());

		List<String> content = new ArrayList<>();
		for (Object phone : phones) {
			JSONObject phoneObject = (JSONObject) phone;
			Map<String, Object> checkParam = new HashMap<>();
			checkParam.put("billId", phoneObject.get("phone"));
			String post = HttpUtil.post("http://fsp-nb.zj.chinamobile.com/api-gateway/nb-moa-server/api-record-m/dz/queryInfo_sys", checkParam);
			try {
				JSONObject postJsonObject = JSONUtil.parseObj(post);
				content.add(phoneObject.get("phone") + " " + postJsonObject.get("data"));
				TimeUnit.MILLISECONDS.sleep(200);
			} catch (Exception e) {
				log.error("验证手机号异常：{}, {}",post, e.getMessage(), e);
			}
		}
		FileUtil.writeLines(content, FILE_PATH, "utf-8");
		redisTemplate.opsForValue().set("mobile:flag", "1");
	}



}
