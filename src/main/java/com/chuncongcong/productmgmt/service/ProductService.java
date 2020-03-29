package com.chuncongcong.productmgmt.service;

import com.chuncongcong.productmgmt.model.po.ProductPo;
import com.chuncongcong.productmgmt.model.vo.ProductQueryVo;
import com.chuncongcong.productmgmt.model.vo.ProductVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.github.pagehelper.Page;

/**
 * @author HU
 * @date 2019/12/22 14:42
 */

public interface ProductService {

	/**
	 * 添加商品
	 * @param productVo
	 */
	ProductVo add(ProductVo productVo, String username);

	/**
	 * 更新货品
	 * @param productVo
	 */
	ProductPo updateProduct(ProductVo productVo);

	/**
	 * 更新sku
	 * @param productVo
	 */
	ProductVo updateSku(ProductVo productVo, String username);

	/**
	 * 获取货品详情（包含商品）
	 * @param productId
	 * @return
	 */
	ProductVo getInfo(Long productId);

	/**
	 * 获取货品详情（不包含商品）
	 * @param productId
	 * @return
	 */
	ProductPo getSimpleInfo(Long productId);

	/**
	 * 获取货品列表
	 * @param paging
	 */
	Page<ProductPo> listProduct(Paging paging, ProductQueryVo productQueryVo);

}
