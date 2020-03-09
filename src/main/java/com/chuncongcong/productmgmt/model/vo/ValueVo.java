package com.chuncongcong.productmgmt.model.vo;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/21 15:18
 */

@Data
public class ValueVo {

	private Long valueId;

	private Long attributeId;

	private String attributeName;

	@NotNull(message = "数据不能为null")
	private String valueName;
}
