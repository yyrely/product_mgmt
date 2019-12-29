package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/27 14:31
 */

@Data
public class SkuDto {

	private Long skuId;

	private Long productId;

	private BigDecimal skuInPrice;

	private BigDecimal skuOutPrice;

	private Integer skuStock;

	private String skuDesc;

	private List<ValueDto> valueList;

}
