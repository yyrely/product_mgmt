package com.chuncongcong.productmgmt.service;

import java.util.List;

import com.chuncongcong.productmgmt.model.po.ValuePo;
import com.chuncongcong.productmgmt.model.vo.ValueVo;

/**
 * @author HU
 * @date 2019/12/21 15:57
 */

public interface ValueService {

	/**
	 * 根据规格获取属性
	 * @param attributeId
	 * @return
	 */
	List<ValuePo> get(Long attributeId);

	/**
	 * 保存规格属性值
	 * @param valueVo
	 */
	ValuePo save(ValueVo valueVo);
}
