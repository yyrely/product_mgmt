package com.chuncongcong.productmgmt.service.impl;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chuncongcong.productmgmt.context.RequestContext;
import com.chuncongcong.productmgmt.dao.SellLogDao;
import com.chuncongcong.productmgmt.model.dto.SellLogDto;
import com.chuncongcong.productmgmt.model.dto.SellNumsDto;
import com.chuncongcong.productmgmt.model.po.SellLogPo;
import com.chuncongcong.productmgmt.model.vo.SellLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.SellLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

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

    @Override
    public Page<SellLogDto> list(Paging paging, SellLogQueryVo sellLogQueryVo) {
        if (sellLogQueryVo.getStartDate() != null) {
            sellLogQueryVo.setStartDateTime(LocalDateTime.of(sellLogQueryVo.getStartDate(), LocalTime.MIN));
        }
        if (sellLogQueryVo.getEndDate() != null) {
            sellLogQueryVo.setEndDateTime(LocalDateTime.of(sellLogQueryVo.getEndDate(), LocalTime.MAX));
        }
        sellLogQueryVo.setStoreId(RequestContext.getUserInfo().getStoreId());
        Page<SellLogDto> page = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
            .doSelectPage(() -> sellLogDao.list(sellLogQueryVo));
        SellNumsDto sellNumsDto = sellLogDao.countNumsAndPrice(sellLogQueryVo);
        page.setTotal(sellNumsDto.getCounts());
        return page;
    }

    @Override
    public SellNumsDto nums(SellLogQueryVo sellLogQueryVo) {
        if (sellLogQueryVo.getStartDate() != null) {
            sellLogQueryVo.setStartDateTime(LocalDateTime.of(sellLogQueryVo.getStartDate(), LocalTime.MIN));
        }
        if (sellLogQueryVo.getEndDate() != null) {
            sellLogQueryVo.setEndDateTime(LocalDateTime.of(sellLogQueryVo.getEndDate(), LocalTime.MAX));
        }
        sellLogQueryVo.setStoreId(RequestContext.getUserInfo().getStoreId());
        return sellLogDao.countNumsAndPrice(sellLogQueryVo);
    }
}
