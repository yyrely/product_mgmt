package com.chuncongcong.productmgmt.service.impl;

import java.util.List;

import com.chuncongcong.productmgmt.dao.CategoryDao;
import com.chuncongcong.productmgmt.model.po.CategoryPo;
import com.chuncongcong.productmgmt.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HU
 * @date 2019/12/20 16:50
 */

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<CategoryPo> list() {
		return categoryDao.selectAll();
	}
}
