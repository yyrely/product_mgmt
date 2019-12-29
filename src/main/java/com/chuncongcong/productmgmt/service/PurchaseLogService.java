package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.po.PurchaseLogPo;

/**
 * @author HU
 * @date 2019/12/22 17:23
 */

public interface PurchaseLogService {
	/**
	 * 保存进货日志
	 * @param purchaseLogPo
	 */
	void save(PurchaseLogPo purchaseLogPo);
}
