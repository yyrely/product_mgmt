package com.chuncongcong.productmgmt.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chuncongcong.productmgmt.dao.SellLogDao;
import com.chuncongcong.productmgmt.exception.ServiceException;
import com.chuncongcong.productmgmt.model.dto.SellLogDto;
import com.chuncongcong.productmgmt.model.dto.TotalNumsDto;
import com.chuncongcong.productmgmt.model.po.SellLogPo;
import com.chuncongcong.productmgmt.model.po.SkuPo;
import com.chuncongcong.productmgmt.model.vo.SellLogQueryVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.service.SellLogService;
import com.chuncongcong.productmgmt.service.SkuService;
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

    @Autowired
    private SkuService skuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
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
        sellLogQueryVo.setStoreId(sellLogQueryVo.getStoreId());
        Page<SellLogDto> page = PageHelper.startPage(paging.getPageNum(), paging.getPageSize())
            .doSelectPage(() -> sellLogDao.list(sellLogQueryVo));
        return page;
    }

    @Override
    public TotalNumsDto nums(SellLogQueryVo sellLogQueryVo) {
        if (sellLogQueryVo.getStartDate() == null) {
            sellLogQueryVo.setStartDate(LocalDate.now());
        }
        if (sellLogQueryVo.getEndDate() == null) {
            sellLogQueryVo.setEndDate(LocalDate.now());
        }
        sellLogQueryVo.setStartDateTime(LocalDateTime.of(sellLogQueryVo.getStartDate(), LocalTime.MIN));
        sellLogQueryVo.setEndDateTime(LocalDateTime.of(sellLogQueryVo.getEndDate(), LocalTime.MAX));
        sellLogQueryVo.setStoreId(sellLogQueryVo.getStoreId());
        return sellLogDao.countNumsAndPrice(sellLogQueryVo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returns(Long sellId, Integer returnNums) {
        if (sellId == null) {
            throw new ServiceException("参数异常");
        }
        SellLogPo sellLogPo = sellLogDao.selectByPrimaryKey(sellId);
        if (sellLogPo == null) {
            throw new ServiceException("参数异常");
        }
        if (returnNums > sellLogPo.getSellNums()) {
            throw new ServiceException("回退数量异常，请检查");
        }
        SkuPo skuPo = skuService.getById(sellLogPo.getSkuId());
        Integer origStockNums = skuPo.getSkuStock();
        skuPo.setSkuStock(origStockNums + returnNums);
        skuService.updateByOrigStockNums(skuPo, origStockNums);
        if (returnNums.equals(sellLogPo.getSellNums())) {
            sellLogDao.deleteByPrimaryKey(sellId);
        } else {
            sellLogPo.setSellNums(sellLogPo.getSellNums() - returnNums);
            sellLogPo.setSellTotal(sellLogPo.getSellPrice().multiply(new BigDecimal(sellLogPo.getSellNums())));
            sellLogDao.updateByPrimaryKey(sellLogPo);
        }
    }
}
