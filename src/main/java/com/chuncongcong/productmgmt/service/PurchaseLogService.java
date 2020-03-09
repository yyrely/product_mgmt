package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.PurchaseLogPo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.github.pagehelper.Page;

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

	/**
	 * 进货日志列表
	 * @param paging
	 */
	Page<PurchaseLogDto> list(Paging paging, PurchaseLogQueryVo purchaseLogQueryVo);

	/**
	 * 进货统计
	 * @param purchaseLogQueryVo
	 * @return
	 */
	TotalNumsDto nums(PurchaseLogQueryVo purchaseLogQueryVo);
}
