package com.chuncongcong.productmgmt.model.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2021/12/24 11:35
 */

@Data
public class PurchaseLogSkuVo {

    private Long skuId;

    private Integer purchaseNums;

    private BigDecimal totalPrice;

    private List<ValueVo> valueList;
}
