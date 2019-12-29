package com.chuncongcong.productmgmt.model.vo;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/22 14:45
 */

@Data
public class ProductVo {

	private Long productId;

	private Long userId;

	@NotNull(message = "数据不能为null")
	private Long categoryId;

	@NotNull(message = "数据不能为null")
	private String productNo;

	private String productName;

	private String productPic;

	private String productDesc;

	private List<SkuVo> skuList;
}
