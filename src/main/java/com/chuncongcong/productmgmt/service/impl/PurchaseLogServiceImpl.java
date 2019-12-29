package com.chuncongcong.productmgmt.service.impl;

import com.chuncongcong.productmgmt.dao.PurchaseLogDao;
import com.chuncongcong.productmgmt.model.po.PurchaseLogPo;
import com.chuncongcong.productmgmt.service.PurchaseLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author HU
 * @date 2019/12/22 17:23
 */

@Service
public class PurchaseLogServiceImpl implements PurchaseLogService {

	@Autowired
	private PurchaseLogDao purchaseLogDao;

	@Override
	public void save(PurchaseLogPo purchaseLogPo) {
		purchaseLogDao.insert(purchaseLogPo);
	}
}
