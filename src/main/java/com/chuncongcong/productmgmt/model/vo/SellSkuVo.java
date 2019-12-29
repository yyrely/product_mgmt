package com.chuncongcong.productmgmt.model.vo;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/27 13:54
 */

@Data
public class SellSkuVo {

	@NotNull(message = "数据不能为null")
	private Long productId;

	@NotNull(message = "数据不能为null")
	private Long skuId;

	@Min(message = "数量最小为0", value = 0)
	private Integer sellNums;

	@Min(message = "金额最小为0", value = 0)
	private BigDecimal sellPrice;
}
