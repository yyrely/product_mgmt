package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.ReturnLogPo;
import com.chuncongcong.productmgmt.model.vo.ReturnLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.github.pagehelper.Page;

/**
 * @author HU
 * @date 2020/3/8 16:49
 */

public interface ReturnLogService {

	/**
	 * 保存
	 * @param returnLogPo
	 */
	void save(ReturnLogPo returnLogPo);

	/**
	 * 查询列表
	 * @param paging
	 * @param returnLogQueryVo
	 * @return
	 */
	Page<PurchaseLogDto> list(Paging paging, ReturnLogQueryVo returnLogQueryVo);

	/**
	 * 回退统计
	 * @param returnLogQueryVo
	 * @return
	 */
	TotalNumsDto nums(ReturnLogQueryVo returnLogQueryVo);
}
