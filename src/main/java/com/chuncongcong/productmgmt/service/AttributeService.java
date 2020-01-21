package com.chuncongcong.productmgmt.service;

import java.util.List;

import com.chuncongcong.productmgmt.model.po.AttributePo;
import com.chuncongcong.productmgmt.model.vo.AttributeVo;

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
	List<AttributeVo> getAttributeAndValue(Long categoryId);

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
