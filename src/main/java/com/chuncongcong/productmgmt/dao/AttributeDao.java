package com.chuncongcong.productmgmt.dao;

import java.util.List;

import com.chuncongcong.productmgmt.model.dto.AttributeDto;
import com.chuncongcong.productmgmt.model.po.AttributePo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HU
 * @date 2019/12/21 14:51
 */

@Mapper
public interface AttributeDao extends MyMapper<AttributePo>{

	/**
	 * 根据分类id获取规格及属性值
	 * @param categoryId
	 * @return
	 */
	List<AttributeDto> getAttributeAndValue(Long categoryId);
}
