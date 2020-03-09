package com.chuncongcong.productmgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.PurchaseLogPo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogQueryVo;

/**
 * @author HU
 * @date 2019/12/22 17:21
 */

@Mapper
public interface PurchaseLogDao extends MyMapper<PurchaseLogPo>{

	/**
	 * 进货日志列表
	 * @return
	 */
	List<PurchaseLogDto> list(@Param("purchaseLogQueryVo") PurchaseLogQueryVo purchaseLogQueryVo);

	/**
	 * 进货统计
	 * @param purchaseLogQueryVo
	 * @return
	 */
	TotalNumsDto countNumsAndPrice(@Param("purchaseLogQueryVo") PurchaseLogQueryVo purchaseLogQueryVo);
}
