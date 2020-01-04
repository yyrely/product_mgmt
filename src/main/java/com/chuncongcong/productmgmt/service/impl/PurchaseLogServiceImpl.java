package com.chuncongcong.productmgmt.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuncongcong.productmgmt.context.RequestContext;
import com.chuncongcong.productmgmt.dao.PurchaseLogDao;
import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.dto.PurchaseNumsDto;
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
        purchaseLogQueryVo.setStoreId(RequestContext.getUserInfo().getStoreId());
        // todo 可手动分页（有重复查询）
        Page<PurchaseLogDto> page = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
            .doSelectPage(() -> purchaseLogDao.list(purchaseLogQueryVo));
        PurchaseNumsDto purchaseNumsDto = purchaseLogDao.countNumsAndPrice(purchaseLogQueryVo);
        page.setTotal(purchaseNumsDto.getCounts());
        return page;
    }

    @Override
    public PurchaseNumsDto nums(PurchaseLogQueryVo purchaseLogQueryVo) {
        purchaseLogQueryVo.setStoreId(RequestContext.getUserInfo().getStoreId());
        if (purchaseLogQueryVo.getStartDate() != null) {
            purchaseLogQueryVo.setStartDateTime(LocalDateTime.of(purchaseLogQueryVo.getStartDate(), LocalTime.MIN));
        }
        if (purchaseLogQueryVo.getEndDate() != null) {
            purchaseLogQueryVo.setEndDateTime(LocalDateTime.of(purchaseLogQueryVo.getEndDate(), LocalTime.MAX));
        }
        return purchaseLogDao.countNumsAndPrice(purchaseLogQueryVo);
    }
}
