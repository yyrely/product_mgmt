package com.chuncongcong.productmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.vo.ReturnLogQueryVo;
import com.chuncongcong.productmgmt.model.vo.ReturnLogVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.page.SimplePagingObject;
import com.chuncongcong.productmgmt.service.ReturnLogService;
import com.github.pagehelper.Page;

/**
 * @author HU
 * @date 2020/3/8 17:47
 */

@RestController
@RequestMapping("/api/return/log")
public class ReturnLogController {

	@Autowired
	private ReturnLogService returnLogService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@GetMapping("/list")
	public Object list(Paging paging, ReturnLogQueryVo returnLogQueryVo) {
		Page<PurchaseLogDto> page = returnLogService.list(paging, returnLogQueryVo);
		List<ReturnLogVo> returnLogVos = modelMapperOperation.mapToList(page.getResult(), ReturnLogVo.class);
		return new SimplePagingObject<>(returnLogVos, paging.getPageNum(), paging.getPageSize(), page.getTotal());
	}

	@GetMapping("/nums")
	public Object nums(ReturnLogQueryVo returnLogQueryVo) {
		return returnLogService.nums(returnLogQueryVo);
	}
}
