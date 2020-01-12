package com.chuncongcong.productmgmt.model.vo;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.constraints.Min;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/22 14:50
 */

@Data
public class SkuVo {

	private Long storeId;

	private Long skuId;

	private Long productId;

	@Min(value = 0, message = "价格最低为0")
	private BigDecimal skuInPrice;

	@Min(value = 0, message = "价格最低为0")
	private BigDecimal skuOutPrice;

	@Min(value = 0, message = "库存最小为0")
	private Integer skuStock;

	private String skuDesc;

	private List<ValueVo> valueList;
}
