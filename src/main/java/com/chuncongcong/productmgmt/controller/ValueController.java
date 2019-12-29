package com.chuncongcong.productmgmt.controller;

import java.util.List;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.po.ValuePo;
import com.chuncongcong.productmgmt.model.vo.ValueVo;
import com.chuncongcong.productmgmt.service.ValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author HU
 * @date 2019/12/21 15:56
 */

@RestController
@RequestMapping("/api/value")
public class ValueController {

	@Autowired
	private ValueService valueService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@GetMapping("/get/{attributeId}")
	public Object get(@PathVariable("attributeId") @Validated Long attributeId) {
		List<ValuePo> valuePos = valueService.get(attributeId);
		return modelMapperOperation.mapToList(valuePos, ValueVo.class);
	}

	@PostMapping("/save")
	public Object save(@RequestBody @Validated ValueVo valueVo) {
		ValuePo valuePo = valueService.save(valueVo);
		return modelMapperOperation.map(valuePo, ValueVo.class);
	}

}
