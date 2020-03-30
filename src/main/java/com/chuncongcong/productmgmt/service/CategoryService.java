package com.chuncongcong.productmgmt.service;

import java.util.List;

import com.chuncongcong.productmgmt.model.po.CategoryPo;

/**
 * @author HU
 * @date 2019/12/20 16:49
 */

public interface CategoryService {

	/**
	 * 分类列表
	 * @return
	 */
	List<CategoryPo> list(Long storeId);

	/**
	 * 分类详情
	 * @param categoryId
	 * @return
	 */
	CategoryPo getById(Long categoryId);
}
