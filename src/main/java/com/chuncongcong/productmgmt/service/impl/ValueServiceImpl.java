package com.chuncongcong.productmgmt.service.impl;

import java.util.List;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.dao.ValueDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.po.AttributePo;
import com.chuncongcong.productmgmt.model.po.ValuePo;
import com.chuncongcong.productmgmt.model.vo.ValueVo;
import com.chuncongcong.productmgmt.service.AttributeService;
import com.chuncongcong.productmgmt.service.CategoryService;
import com.chuncongcong.productmgmt.service.ValueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HU
 * @date 2019/12/21 15:57
 */

@Service
public class ValueServiceImpl implements ValueService {

	@Autowired
	private ValueDao valueDao;

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private ModelMapperOperation modelMapperOperation;

	@Override
	public List<ValuePo> get(Long attributeId) {
		if(attributeId == null) {
			throw new ServiceException("参数异常");
		}
		ValuePo query = new ValuePo();
		query.setAttributeId(attributeId);
		return valueDao.select(query);
	}

	@Override
	public ValuePo save(ValueVo valueVo) {
		attributeService.getById(valueVo.getAttributeId());
		ValuePo valuePo = modelMapperOperation.map(valueVo, ValuePo.class);
		valueDao.insert(valuePo);
		return valuePo;
	}
}
