package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2020/3/8 19:40
 */

@Data
public class ReturnLogDto {

	private Long returnId;

	private Long productId;

	private String productName;

	private String productNo;

	private Long skuId;

	private Integer returnNums;

	private BigDecimal totalPrice;

	private LocalDateTime returnDate;

	private List<ValueDto> valueList;

}
