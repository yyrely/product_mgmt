package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/30 11:31
 */

@Data
public class PurchaseLogDto {

	private Long purchaseId;

	private Long productId;

	private String productName;

	private String productNo;

	private Long skuId;

	private Integer purchaseNums;

	private BigDecimal totalPrice;

	private LocalDateTime purchaseDate;

	private List<ValueDto> valueList;
}
