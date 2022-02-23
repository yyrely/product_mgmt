package com.chuncongcong.productmgmt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chuncongcong.productmgmt.config.authorization.AuthUser;
import com.chuncongcong.productmgmt.config.modelMapper.ModelMapperOperation;
import com.chuncongcong.productmgmt.model.dto.PurchaseLogDto;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogMergeVo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogQueryVo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogSkuVo;
import com.chuncongcong.productmgmt.model.vo.PurchaseLogVo;
import com.chuncongcong.productmgmt.page.Paging;
import com.chuncongcong.productmgmt.page.SimplePagingObject;
import com.chuncongcong.productmgmt.service.PurchaseLogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;

import lombok.extern.slf4j.Slf4j;

/**
 * @author HU
 * @date 2019/12/30 11:23
 */

@Slf4j
@RestController
@RequestMapping("/api/purchase/log")
public class PurchaseLogController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PurchaseLogService purchaseLogService;

    @Autowired
    private ModelMapperOperation modelMapperOperation;

    @GetMapping("/list")
    public Object list(Paging paging, PurchaseLogQueryVo purchaseLogQueryVo, Authentication authentication)
        throws Exception {
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        purchaseLogQueryVo.setStoreId(authUser.getStoreId());
        Page<PurchaseLogDto> page = purchaseLogService.list(paging, purchaseLogQueryVo);
        List<PurchaseLogVo> purchaseLogVos = modelMapperOperation.mapToList(page.getResult(), PurchaseLogVo.class);
        return new SimplePagingObject<>(purchaseLogVos, paging.getPageNum(), paging.getPageSize(), page.getTotal());
    }

    @GetMapping("/nums")
    public Object nums(PurchaseLogQueryVo purchaseLogQueryVo, Authentication authentication) {
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        purchaseLogQueryVo.setStoreId(authUser.getStoreId());
        return purchaseLogService.nums(purchaseLogQueryVo);
    }

    /**
     * 返回进货列表（相同货号的合并在同一个进货单中）
     * 
     * @param paging
     *            分页信息
     * @param purchaseLogQueryVo
     *            查询信息
     * @param authentication
     *            用户信息
     * @return 分页进货列表
     */
    @GetMapping("/list/merge")
    public Object listMerge(Paging paging, PurchaseLogQueryVo purchaseLogQueryVo, Authentication authentication) {
        AuthUser authUser = (AuthUser)authentication.getPrincipal();
        purchaseLogQueryVo.setStoreId(authUser.getStoreId());
        Page<PurchaseLogDto> page = purchaseLogService.list(paging, purchaseLogQueryVo);
        List<PurchaseLogMergeVo> purchaseLogMergeVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(page.getResult())) {
            PurchaseLogMergeVo lastPurchaseLogMergeVo = null;
            for (PurchaseLogDto purchaseLogDto : page.getResult()) {
                if (Objects.isNull(lastPurchaseLogMergeVo)
                    || (!lastPurchaseLogMergeVo.getProductNo().equals(purchaseLogDto.getProductNo())
                        && !lastPurchaseLogMergeVo.getPurchaseDate().equals(purchaseLogDto.getPurchaseDate()))) {
                    lastPurchaseLogMergeVo = modelMapperOperation.map(purchaseLogDto, PurchaseLogMergeVo.class);
                    purchaseLogMergeVos.add(lastPurchaseLogMergeVo);
                }
                List<PurchaseLogSkuVo> purchaseLogSkuVoList = lastPurchaseLogMergeVo.getPurchaseLogSkuVoList();
                PurchaseLogSkuVo purchaseLogSkuVo = modelMapperOperation.map(purchaseLogDto, PurchaseLogSkuVo.class);
                purchaseLogSkuVoList.add(purchaseLogSkuVo);
            }
        }
        return new SimplePagingObject<>(purchaseLogMergeVos, paging.getPageNum(), paging.getPageSize(),
            page.getTotal());
    }
}
