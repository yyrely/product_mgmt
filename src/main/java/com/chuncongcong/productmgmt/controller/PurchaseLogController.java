package com.chuncongcong.productmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogQueryVo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.page.SimplePagingObject;
import com.chuncongcong.productmgmt.service.PurchaseLogService;
import com.github.pagehelper.Page;

/**
 * @author HU
 * @date 2019/12/30 11:23
 */

@RestController
@RequestMapping("/api/purchase/log")
public class PurchaseLogController {

    @Autowired
    private PurchaseLogService purchaseLogService;

    @Autowired
    private ModelMapperOperation modelMapperOperation;

    @GetMapping("/list")
    public Object list(Paging paging, PurchaseLogQueryVo purchaseLogQueryVo) {
        Page<PurchaseLogDto> page = purchaseLogService.list(paging, purchaseLogQueryVo);
        List<PurchaseLogVo> purchaseLogVos = modelMapperOperation.mapToList(page.getResult(), PurchaseLogVo.class);
        return new SimplePagingObject<>(purchaseLogVos, paging.getPageNum(), paging.getPageSize(), page.getTotal());
    }

    @GetMapping("/nums")
    public Object nums(PurchaseLogQueryVo purchaseLogQueryVo) {
        return purchaseLogService.nums(purchaseLogQueryVo);
    }
}
