package com.chuncongcong.productmgmt.service;

import java.util.List;

import com.chuncongcong.productmgmt.model.dto.AttributeDto;
import com.chuncongcong.productmgmt.model.po.AttributePo;

/**
 * @author HU
 * @date 2019/12/21 14:47
 */

public interface AttributeService {

	/**
	 * 根据分类查询规格及属性信息
	 * @param categoryId
	 * @return
	 */
	List<AttributeDto> getAttributeAndValue(Long categoryId);

	/**
	 * 根据分类查询规格信息
	 * @param categoryId
	 * @return
	 */
	List<AttributePo> getList(Long categoryId);

	/**
	 * 根据分类查询规格信息
	 * @param attributeId
	 * @return
	 */
	AttributePo getById(Long attributeId);
}
