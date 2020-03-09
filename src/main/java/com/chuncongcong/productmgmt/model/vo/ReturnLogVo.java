package com.chuncongcong.productmgmt.model.vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2020/3/8 20:00
 */

@Data
public class ReturnLogVo {

	private Long returnId;

	private Long productId;

	private String productName;

	private String productNo;

	private Long skuId;

	private Integer returnNums;

	private BigDecimal totalPrice;

	private String returnDate;

	private List<ValueVo> valueList;

}
