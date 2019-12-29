package com.chuncongcong.productmgmt.config.restTemplate;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author HU
 * @date 2019/12/29 15:08
 */

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return restTemplate;
	}
}
