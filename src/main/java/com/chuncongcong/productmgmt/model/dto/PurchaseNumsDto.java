package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 15:31
 */

@Data
public class PurchaseNumsDto {

	private Integer counts;

	private Integer totalNums;

	private BigDecimal totalPrices;

}
