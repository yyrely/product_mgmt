package com.chuncongcong.productmgmt.service.impl;

import com.chuncongcong.productmgmt.dao.SellLogDao;
import com.chuncongcong.productmgmt.model.po.SellLogPo;
import com.chuncongcong.productmgmt.service.SellLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HU
 * @date 2019/12/27 11:00
 */

@Service
public class SellLogServiceImpl implements SellLogService {

	@Autowired
	private SellLogDao sellLogDao;

	@Override
	public void save(SellLogPo sellLogPo) {
		sellLogDao.insert(sellLogPo);
	}
}
