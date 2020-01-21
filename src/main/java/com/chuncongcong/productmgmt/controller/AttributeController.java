package com.chuncongcong.productmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.po.AttributePo;
import com.chuncongcong.productmgmt.model.vo.AttributeVo;
import com.chuncongcong.productmgmt.service.AttributeService;

/**
 * @author HU
 * @date 2019/12/21 14:44
 */

@RestController
@RequestMapping("/api/attribute")
public class AttributeController {

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@GetMapping("/value/get/{categoryId}")
	public Object getAttributeAndValue(@PathVariable("categoryId") Long categoryId) {
		return attributeService.getAttributeAndValue(categoryId);
	}

	@GetMapping("/get/{categoryId}")
	public Object get(@PathVariable("categoryId") Long categoryId) {
		List<AttributePo> attributePos = attributeService.getList(categoryId);
		return modelMapperOperation.mapToList(attributePos, AttributeVo.class);
	}

}
