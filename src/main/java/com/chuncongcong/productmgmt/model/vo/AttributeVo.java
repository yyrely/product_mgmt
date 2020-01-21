package com.chuncongcong.productmgmt.model.vo;

import java.util.List;

import lombok.Data;

/**
 * @author HU
 * @date 2019/12/21 15:17
 */

@Data
public class AttributeVo {

	private Long attributeId;

	private Long categoryId;

	private String attributeName;

	private List<Long> valueIds;

	private List<String> valueNames;

	private List<ValueVo> valuePos;
}
