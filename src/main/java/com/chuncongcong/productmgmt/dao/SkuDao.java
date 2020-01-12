package com.chuncongcong.productmgmt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.chuncongcong.productmgmt.model.dto.SkuDto;
import com.chuncongcong.productmgmt.model.dto.SkuNumsDto;
import com.chuncongcong.productmgmt.model.po.SkuPo;

/**
 * @author HU
 * @date 2019/12/22 15:30
 */

@Mapper
public interface SkuDao extends MyMapper<SkuPo>{

	/**
	 * 根据货品id获取商品
	 * @param productId
	 * @return
	 */
	List<SkuDto> getListByProductId(Long productId);

	/**
	 * 货品统计
	 * @return
	 */
	SkuNumsDto countNumsAndPrice(@Param("storeId") Long storeId);

}
