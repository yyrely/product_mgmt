package com.chuncongcong.productmgmt.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuncongcong.productmgmt.dao.PurchaseLogDao;
import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.PurchaseLogPo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.PurchaseLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @author HU
 * @date 2019/12/22 17:23
 */

@Service
public class PurchaseLogServiceImpl implements PurchaseLogService {

    @Autowired
    private PurchaseLogDao purchaseLogDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(PurchaseLogPo purchaseLogPo) {
        purchaseLogDao.insert(purchaseLogPo);
    }

    @Override
    public Page<PurchaseLogDto> list(Paging paging, PurchaseLogQueryVo purchaseLogQueryVo) {
        if (purchaseLogQueryVo.getStartDate() != null) {
            purchaseLogQueryVo.setStartDateTime(LocalDateTime.of(purchaseLogQueryVo.getStartDate(), LocalTime.MIN));
        }
        if (purchaseLogQueryVo.getEndDate() != null) {
            purchaseLogQueryVo.setEndDateTime(LocalDateTime.of(purchaseLogQueryVo.getEndDate(), LocalTime.MAX));
        }
        purchaseLogQueryVo.setStoreId(purchaseLogQueryVo.getStoreId());
        // 因为一对多，产生的数据是多条，导致总数不对
        return PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
            .doSelectPage(() -> purchaseLogDao.list(purchaseLogQueryVo));
    }

    @Override
    public TotalNumsDto nums(PurchaseLogQueryVo purchaseLogQueryVo) {
        purchaseLogQueryVo.setStoreId(purchaseLogQueryVo.getStoreId());
        if (purchaseLogQueryVo.getStartDate() == null) {
            purchaseLogQueryVo.setStartDate(LocalDate.now());
        }
        if (purchaseLogQueryVo.getEndDate() == null) {
            purchaseLogQueryVo.setEndDate(LocalDate.now());
        }
        purchaseLogQueryVo.setStartDateTime(LocalDateTime.of(purchaseLogQueryVo.getStartDate(), LocalTime.MIN));
        purchaseLogQueryVo.setEndDateTime(LocalDateTime.of(purchaseLogQueryVo.getEndDate(), LocalTime.MAX));
        return purchaseLogDao.countNumsAndPrice(purchaseLogQueryVo);
    }
}
