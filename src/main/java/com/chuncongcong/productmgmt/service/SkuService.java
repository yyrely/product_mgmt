package com.chuncongcong.productmgmt.service;

import java.util.List;

import com.chuncongcong.productmgmt.model.dto.SkuDto;
import com.chuncongcong.productmgmt.model.po.SkuPo;
import com.chuncongcong.productmgmt.model.vo.SellSkuVo;

/**
 * @author HU
 * @date 2019/12/22 15:29
 */

public interface SkuService {
	/**
	 * 增加商品
	 * @param skuPo
	 */
	void add(SkuPo skuPo);

	/**
	 * 根据id获取
	 * @param skuId
	 * @return
	 */
	SkuPo getById(Long skuId);

	/**
	 * 更新商品
	 * @param skuPo
	 */
	void update(SkuPo skuPo);

	/**
	 * 根据原始库存数更新（乐观锁）
	 * @param skuPo
	 * @param origStockNums
	 */
	void updateByOrigStockNums(SkuPo skuPo, Integer origStockNums);

	/**
	 * 获取商品列表
	 * @param productId
	 * @return
	 */
	List<SkuDto> getListByProductId(Long productId);

	/**
	 * 出售商品
	 * @param sellSkuVo
	 */
	void sellSku(SellSkuVo sellSkuVo);
}
