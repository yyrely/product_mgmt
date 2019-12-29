package com.chuncongcong.productmgmt.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/27 14:33
 */

@Data
public class ValueDto {

	private Long valueId;

	private Long attributeId;

	private String attributeName;

	private String valueName;
}
