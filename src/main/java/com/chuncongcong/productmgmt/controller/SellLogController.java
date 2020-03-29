package com.chuncongcong.productmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.authorization.AuthUser;
import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.dto.SellLogDto;
import com.chuncongcong.productmgmt.model.vo.SellLogQueryVo;
import com.chuncongcong.productmgmt.model.vo.SellLogVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.page.SimplePagingObject;
import com.chuncongcong.productmgmt.service.SellLogService;
import com.github.pagehelper.Page;

/**
 * @author HU
 * @date 2020/1/4 15:53
 */

@RestController
@RequestMapping("/api/sell/log")
public class SellLogController {

	@Autowired
	private SellLogService sellLogService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@PostMapping("/returns/{sellId}/{returnNums}")
	public Object returns(@PathVariable("sellId") Long sellId, @PathVariable("returnNums") Integer returnNums) {
		sellLogService.returns(sellId, returnNums);
		return null;
	}

	@GetMapping("/list")
	public Object list(Paging paging, SellLogQueryVo sellLogQueryVo, Authentication authentication) {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		sellLogQueryVo.setStoreId(authUser.getStoreId());
		Page<SellLogDto> page = sellLogService.list(paging, sellLogQueryVo);
		List<SellLogVo> sellLogVos = modelMapperOperation.mapToList(page.getResult(), SellLogVo.class);
		return new SimplePagingObject<>(sellLogVos, paging.getPageNum(), paging.getPageSize(), page.getTotal());
	}

	@GetMapping("/nums")
	public Object nums(SellLogQueryVo sellLogQueryVo, Authentication authentication) {
		AuthUser authUser = (AuthUser) authentication.getPrincipal();
		sellLogQueryVo.setStoreId(authUser.getStoreId());
		return sellLogService.nums(sellLogQueryVo);
	}
}
