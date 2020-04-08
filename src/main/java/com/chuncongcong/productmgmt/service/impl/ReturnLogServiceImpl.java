package com.chuncongcong.productmgmt.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuncongcong.productmgmt.dao.ReturnLogDao;
import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.ReturnLogPo;
import com.chuncongcong.productmgmt.model.vo.ReturnLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.ReturnLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author HU
 * @date 2020/3/8 16:50
 */

@Service
public class ReturnLogServiceImpl implements ReturnLogService {

	@Autowired
	private ReturnLogDao returnLogDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(ReturnLogPo returnLogPo) {
		returnLogDao.insert(returnLogPo);
	}

	@Override
	public Page<PurchaseLogDto> list(Paging paging, ReturnLogQueryVo returnLogQueryVo) {
		if (returnLogQueryVo.getStartDate() != null) {
			returnLogQueryVo.setStartDateTime(LocalDateTime.of(returnLogQueryVo.getStartDate(), LocalTime.MIN));
		}
		if (returnLogQueryVo.getEndDate() != null) {
			returnLogQueryVo.setEndDateTime(LocalDateTime.of(returnLogQueryVo.getEndDate(), LocalTime.MAX));
		}
		returnLogQueryVo.setStoreId(returnLogQueryVo.getStoreId());
		Page<PurchaseLogDto> page = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
				.doSelectPage(() -> returnLogDao.list(returnLogQueryVo));
		return page;
	}

	@Override
	public TotalNumsDto nums(ReturnLogQueryVo returnLogQueryVo) {
		returnLogQueryVo.setStoreId(returnLogQueryVo.getStoreId());
		if(returnLogQueryVo.getStartDate() == null) {
			returnLogQueryVo.setStartDate(LocalDate.now());
		}
		if(returnLogQueryVo.getEndDate() == null) {
			returnLogQueryVo.setEndDate(LocalDate.now());
		}
		returnLogQueryVo.setStartDateTime(LocalDateTime.of(returnLogQueryVo.getStartDate(), LocalTime.MIN));
		returnLogQueryVo.setEndDateTime(LocalDateTime.of(returnLogQueryVo.getEndDate(), LocalTime.MAX));
		return returnLogDao.countNumsAndPrice(returnLogQueryVo);
	}
}
