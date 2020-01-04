package com.chuncongcong.productmgmt.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2020/1/4 16:19
 */

@Data
public class SellLogDto {

	private Long sellId;

	private Long productId;

	private String productName;

	private String productNo;

	private Long skuId;

	private Integer sellNums;

	private BigDecimal sellPrice;

	private BigDecimal sellTotal;

	private LocalDateTime sellDate;

	private String sellUsername;

	private List<ValueDto> valueList;

}
