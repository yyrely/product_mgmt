package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.dto.SellLogDto;
import com.chuncongcong.productmgmt.model.dto.SellNumsDto;
import com.chuncongcong.productmgmt.model.po.SellLogPo;
import com.chuncongcong.productmgmt.model.vo.SellLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.github.pagehelper.Page;

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

	/**
	 * 出售日志列表
	 * @param paging
	 * @param sellLogQueryVo
	 */
	Page<SellLogDto> list(Paging paging, SellLogQueryVo sellLogQueryVo);

	/**
	 * 出售统计
	 * @param sellLogQueryVo
	 * @return
	 */
	SellNumsDto nums(SellLogQueryVo sellLogQueryVo);

	/**
	 * 退货
	 * @param sellId
	 */
	void returns(Long sellId);
}
