package com.chuncongcong.productmgmt.controller;

import java.util.List;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.po.CategoryPo;
import com.chuncongcong.productmgmt.model.vo.CategoryVo;
import com.chuncongcong.productmgmt.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HU
 * @date 2019/12/20 16:47
 */

@RestController
@RequestMapping("/api/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@GetMapping("/list")
	public Object list() {
		List<CategoryPo> list = categoryService.list();
		return modelMapperOperation.mapToList(list, CategoryVo.class);
	}

}
