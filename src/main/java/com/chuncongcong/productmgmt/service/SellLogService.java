package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.po.SellLogPo;

/**
 * @author HU
 * @date 2019/12/27 11:00
 */

public interface SellLogService {

	/**
	 * 保存售出日志
	 * @param sellLogPo
	 */
	void save(SellLogPo sellLogPo);
}
