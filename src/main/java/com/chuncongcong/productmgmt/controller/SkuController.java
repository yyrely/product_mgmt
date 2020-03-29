package com.chuncongcong.productmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.authorization.AuthUser;
import com.chuncongcong.productmgmt.model.vo.SellSkuVo;
import com.chuncongcong.productmgmt.service.SkuService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HU
 * @date 2019/12/27 17:17
 */

@Slf4j
@RestController
@RequestMapping("/api/sku")
public class SkuController {

	@Autowired
	private SkuService skuService;

	@PostMapping("/sell")
	public Object sellSku(@RequestBody @Validated SellSkuVo sellSkuVo, Authentication authentication) {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		skuService.sellSku(sellSkuVo, authUser.getName());
		return null;
	}

	@PostMapping("/return")
	public Object returnSku(@RequestBody @Validated SellSkuVo sellSkuVo, Authentication authentication) {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		skuService.returnSku(sellSkuVo, authUser.getName());
		return null;
	}

	@GetMapping("/nums")
	public Object nums(Authentication authentication) {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		return skuService.nums(authUser.getStoreId());
	}


}
