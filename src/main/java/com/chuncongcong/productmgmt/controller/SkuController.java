package com.chuncongcong.productmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.model.vo.SellSkuVo;
import com.chuncongcong.productmgmt.service.SkuService;

/**
 * @author HU
 * @date 2019/12/27 17:17
 */

@RestController
@RequestMapping("/api/sku")
public class SkuController {

	@Autowired
	private SkuService skuService;

	@PostMapping("/sell")
	public Object sellSku(@RequestBody @Validated SellSkuVo sellSkuVo) {
		skuService.sellSku(sellSkuVo);
		return null;
	}

	@GetMapping("/nums")
	public Object nums() {
		return skuService.nums();
	}


}
