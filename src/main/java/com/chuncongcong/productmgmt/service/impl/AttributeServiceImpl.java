package com.chuncongcong.productmgmt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuncongcong.productmgmt.dao.AttributeDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.dto.AttributeDto;
import com.chuncongcong.productmgmt.model.po.AttributePo;
import com.chuncongcong.productmgmt.service.AttributeService;

/**
 * @author HU
 * @date 2019/12/21 14:50
 */

@Service
public class AttributeServiceImpl implements AttributeService {

	@Autowired
	private AttributeDao attributeDao;

	@Override
	public List<AttributeDto> getAttributeAndValue(Long categoryId) {
		if(categoryId == null) {
			throw new ServiceException("参数异常");
		}
		return attributeDao.getAttributeAndValue(categoryId);
	}

	@Override
	public List<AttributePo> getList(Long categoryId) {
		if(categoryId == null) {
			throw new ServiceException("参数异常");
		}
		AttributePo query = new AttributePo();
		query.setCategoryId(categoryId);
		return attributeDao.select(query);
	}

	@Override
	public AttributePo getById(Long attributeId) {
		if(attributeId == null) {
			throw new ServiceException("参数异常");
		}
		AttributePo attributePo = attributeDao.selectByPrimaryKey(attributeId);
		if(attributePo == null) {
			throw new ServiceException("参数异常");
		}
		return attributePo;
	}
}


