package com.chuncongcong.productmgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chuncongcong.productmgmt.model.dto.SellLogDto;
import com.chuncongcong.productmgmt.model.dto.SellNumsDto;
import com.chuncongcong.productmgmt.model.po.SellLogPo;
import com.chuncongcong.productmgmt.model.vo.SellLogQueryVo;

/**
 * @author HU
 * @date 2019/12/27 10:59
 */

@Mapper
public interface SellLogDao extends MyMapper<SellLogPo>{

	/**
	 * 售货列表
	 * @param sellLogQueryVo
	 */
	List<SellLogDto> list(@Param("sellLogQueryVo") SellLogQueryVo sellLogQueryVo);

	/**
	 * 售货统计
	 * @param sellLogQueryVo
	 * @return
	 */
	SellNumsDto countNumsAndPrice(@Param("sellLogQueryVo") SellLogQueryVo sellLogQueryVo);
}
