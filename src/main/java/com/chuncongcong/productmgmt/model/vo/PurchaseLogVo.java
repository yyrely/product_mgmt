package com.chuncongcong.productmgmt.model.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/30 15:54
 */

@Data
public class PurchaseLogVo {

	private Long purchaseId;

	private Long productId;

	private String productName;

	private String productNo;

	private Long skuId;

	private Integer purchaseNums;

	private BigDecimal totalPrice;

	private String purchaseDate;

	private List<ValueVo> valueList;

}
