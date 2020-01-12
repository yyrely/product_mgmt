package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 17:11
 */

@Data
public class SkuNumsDto {

	private Integer totalNums = 0;

	private BigDecimal totalPrice = BigDecimal.ZERO;
}
