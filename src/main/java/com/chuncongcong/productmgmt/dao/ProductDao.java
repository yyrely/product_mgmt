package com.chuncongcong.productmgmt.dao;

import java.util.List;
import java.util.Set;

import com.chuncongcong.productmgmt.model.dto.SkuPurchaseDto;
import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author HU
 * @date 2019/12/22 14:43
 */

@Mapper
public interface ProductDao extends MyMapper<ProductPo>{

	/**
	 * 获取货品所有规格
	 * @param productId
	 * @return
	 */
	Set<Long> getAttributes(Long productId);

	/**
	 * 获取商品所有属性
	 * @param productId
	 * @return
	 */
	Set<Long> getValues(Long productId);


	/**
	 * 获取商品列表
	 * @param productQueryVo
	 * @return
	 */
	List<ProductVo> listProduct(@Param("productQueryVo") ProductQueryVo productQueryVo);

	/**
	 * 获取商品所有sku的进货数量
	 * @param productNo
	 */
    List<SkuPurchaseDto> productPurchaseNums(String productNo);
}
