package com.chuncongcong.productmgmt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.dao.UserInfoDao;
import org.modelmapper.ModelMapper;

/**
 * @author HU
 * @date 2019/12/18 17:16
 */

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private ModelMapperOperation modelMapperUtils;

	@Autowired
	private ApplicationContext applicationContext;
	@GetMapping
	public Object test(Long i) {
		ModelMapper modelMapper1 = (ModelMapper)applicationContext.getBean("modelMapper");
		ModelMapper modelMapper2 = (ModelMapper)applicationContext.getBean("modelMapper");
		return modelMapper1 == modelMapper2;
	}

}
