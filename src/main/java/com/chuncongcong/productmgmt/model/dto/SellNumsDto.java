package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 16:39
 */

@Data
public class SellNumsDto {

	private Integer counts;

	private Integer totalNums;

	private BigDecimal totalPrices;
}
