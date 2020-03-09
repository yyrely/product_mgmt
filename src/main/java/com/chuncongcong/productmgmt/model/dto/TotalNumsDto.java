package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 16:39
 */

@Data
public class TotalNumsDto {

	private Integer counts = 0;

	private Integer totalNums = 0;

	private BigDecimal totalPrices = BigDecimal.ZERO;
}
